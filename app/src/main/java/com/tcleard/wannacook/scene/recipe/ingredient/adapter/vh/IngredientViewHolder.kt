package com.tcleard.wannacook.scene.recipe.ingredient.adapter.vh

import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.IngredientViewModel
import com.tcleard.wannacook.ui.adapter.AViewHolder
import kotlinx.android.synthetic.main.itemview_ingredient.view.*

class IngredientViewHolder(
        parent: ViewGroup
) : AViewHolder<IngredientViewModel>(parent, R.layout.itemview_ingredient) {

    init {
        itemView.ingredientRoot.setOnClickListener {
            itemView.ingredientCheck.toggle()
        }
    }

    override fun bind(item: IngredientViewModel) {
        super.bind(item)

        val components = arrayListOf<String>()

        item.getQuantity()?.let { components.add(it) }
        item.getUnit()?.let { components.add(resources.getString(it).decapitalize()) }

        if (item.hasConnector()) {
            components.add(resources.getString(R.string.connector))
        }

        components.add(item.getName())

        itemView.ingredientContent.text = components.joinToString(" ")

    }

}