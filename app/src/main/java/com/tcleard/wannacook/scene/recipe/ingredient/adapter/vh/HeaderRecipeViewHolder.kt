package com.tcleard.wannacook.scene.recipe.ingredient.adapter.vh

import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.HeaderRecipeViewModel
import kotlinx.android.synthetic.main.itemview_recipe_header.view.*

class HeaderRecipeViewHolder(
        parent: ViewGroup
) : ARecipeViewHolder<HeaderRecipeViewModel>(parent, R.layout.itemview_recipe_header) {

    override fun bind(item: HeaderRecipeViewModel) {
        super.bind(item)
        itemView.recipeHeaderText.setText(item.getTextRes())
        itemView.recipeHeaderIcon.setImageResource(item.getIconRes())
    }

}