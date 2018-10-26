package com.tcleard.wannacook.scene.recipe.ingredient.adapter

import android.view.ViewGroup
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vh.DetailIngredientViewHolder
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.DetailIngredientViewModel
import com.tcleard.wannacook.ui.adapter.AViewModelAdapter

class DetailIngredientAdapter : AViewModelAdapter<DetailIngredientViewModel, DetailIngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailIngredientViewHolder =
            DetailIngredientViewHolder(parent)

    override fun onBindViewHolder(holder: DetailIngredientViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getSpanCount(): Int = 2

    override fun getSpanSize(position: Int): Int = 1

}