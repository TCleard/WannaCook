package com.tcleard.wannacook.scene.recipe.ingredient.adapter

import android.view.ViewGroup
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vh.IngredientViewHolder
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.IngredientViewModel
import com.tcleard.wannacook.ui.adapter.AViewModelAdapter

class IngredientAdapter : AViewModelAdapter<IngredientViewModel, IngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder =
            IngredientViewHolder(parent)

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getSpanCount(): Int = 2

    override fun getSpanSize(position: Int): Int = 1

}