package com.tcleard.wannacook.scene.recipe.ingredient.adapter.vh

import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.IngredientRecipeViewModel
import kotlinx.android.synthetic.main.itemview_recipe_ingredient.view.*

class IngredientRecipeViewHolder(
        parent: ViewGroup
) : ARecipeViewHolder<IngredientRecipeViewModel>(parent, R.layout.itemview_recipe_ingredient) {

    override fun bind(item: IngredientRecipeViewModel) {
        super.bind(item)

        val components = arrayListOf<String>()

        item.getQuantity()?.let { components.add(it) }
        item.getUnit()?.let { components.add(resources.getString(it)) }

        if (item.hasConnector()) {
            components.add(resources.getString(R.string.connector))
        }

        components.add(item.getName())

        itemView.ingredientRecipeContent.text = components.joinToString(" ")

    }

}