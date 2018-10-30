package com.tcleard.wannacook.scene.main.adapter.vh

import android.content.res.ColorStateList
import android.support.v4.widget.ImageViewCompat
import android.view.View
import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.extension.withAlpha
import com.tcleard.wannacook.core.manager.IImageManager
import com.tcleard.wannacook.scene.main.adapter.vm.RecipeViewModel
import kotlinx.android.synthetic.main.itemview_recipe.view.*

class RecipeViewHolder(
        parent: ViewGroup,
        private val imageManager: IImageManager
) : ARecipeViewHolder(parent, R.layout.itemview_recipe) {

    private var imageRequest: IImageManager.ImageRequest? = null

    init {
        itemView.recipeRoot.setOnClickListener {
            item?.onClick(itemView.recipeImage)
        }
    }

    override fun bind(item: RecipeViewModel) {
        super.bind(item)

        val color = item.getColor() ?: resources.getColor(R.color.colorAccent)

        ImageViewCompat.setImageTintList(itemView.recipeBackground, ColorStateList.valueOf(color))

        itemView.recipeImage.setBackgroundColor(color.withAlpha(0.6f))

        if (item.getImageUrl().isNotBlank()) {
            if (imageRequest?.url != item.getImageUrl()) {
                imageRequest?.cancel()
                imageRequest = imageManager.loadImage(item.getImageUrl(), itemView.recipeImage)
            }
        } else {
            imageRequest?.cancel()
            imageRequest = null
            itemView.recipeImage.setImageDrawable(null)
        }

        itemView.recipeName.text = item.getName()

        if (item.getPreparationTime() != null) {
            itemView.recipePreparationIcon.visibility = View.VISIBLE
            itemView.recipePreparation.visibility = View.VISIBLE
            itemView.recipePreparation.text = item.getPreparationTime()!!
        } else {
            itemView.recipePreparationIcon.visibility = View.GONE
            itemView.recipePreparation.visibility = View.GONE
        }

        if (item.getCookingTime() != null) {
            itemView.recipeCookingIcon.visibility = View.VISIBLE
            itemView.recipeCooking.visibility = View.VISIBLE
            itemView.recipeCooking.text = item.getCookingTime()!!
        } else {
            itemView.recipeCookingIcon.visibility = View.GONE
            itemView.recipeCooking.visibility = View.GONE
        }

        if (item.getDescription().isNotBlank()) {
            itemView.recipeDescription.text = item.getDescription()
            itemView.recipeDescription.visibility = View.VISIBLE
        } else {
            itemView.recipeDescription.visibility = View.GONE
        }

        if (item.isFavorite()) {
            itemView.recipeFavorite.setImageResource(R.mipmap.favorite_on)
        } else {
            itemView.recipeFavorite.setImageResource(R.mipmap.favorite_off)
        }

    }

}