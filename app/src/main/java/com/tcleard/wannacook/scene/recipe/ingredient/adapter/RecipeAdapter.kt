package com.tcleard.wannacook.scene.recipe.ingredient.adapter

import android.view.ViewGroup
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vh.ARecipeViewHolder
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vh.HeaderRecipeViewHolder
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vh.IngredientRecipeViewHolder
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vh.StepRecipeViewHolder
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.HeaderRecipeViewModel
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.IRecipeViewModel
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.IngredientRecipeViewModel
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.StepRecipeViewModel
import com.tcleard.wannacook.ui.adapter.AItemAdapter

class RecipeAdapter : AItemAdapter<IRecipeViewModel, ARecipeViewHolder<*>>() {

    companion object {

        const val TYPE_HEADER = 1
        const val TYPE_INGREDIENT = 2
        const val TYPE_STEP = 3

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ARecipeViewHolder<*> =
            when (viewType) {
                TYPE_HEADER -> HeaderRecipeViewHolder(parent)
                TYPE_INGREDIENT -> IngredientRecipeViewHolder(parent)
                else -> StepRecipeViewHolder(parent)
            }

    override fun onBindViewHolder(holder: ARecipeViewHolder<*>, position: Int) {
        val item = items[position]
        when {
            holder is HeaderRecipeViewHolder && item is HeaderRecipeViewModel -> {
                holder.bind(item)
            }
            holder is IngredientRecipeViewHolder && item is IngredientRecipeViewModel -> {
                holder.bind(item)
            }
            holder is StepRecipeViewHolder && item is StepRecipeViewModel -> {
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
            when (items[position]) {
                is HeaderRecipeViewModel -> TYPE_HEADER
                is IngredientRecipeViewModel -> TYPE_INGREDIENT
                else -> TYPE_STEP
            }

    override fun getSpanCount(): Int = 2

    override fun getSpanSize(position: Int): Int =
            when (getItemViewType(position)) {
                TYPE_INGREDIENT -> 1
                else -> 2
            }

}