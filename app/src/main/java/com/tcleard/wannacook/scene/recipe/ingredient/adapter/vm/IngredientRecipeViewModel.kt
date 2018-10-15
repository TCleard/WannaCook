package com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm

import com.tcleard.wannacook.core.model.Ingredient

class IngredientRecipeViewModel(
        private val ingredient: Ingredient
) : IRecipeViewModel {

    fun getQuantity(): String? = if (ingredient.quantity > 0) {
        if (ingredient.unit == Ingredient.Unit.NONE) {
            "${ingredient.quantity.toInt()}"
        } else {
            "${ingredient.quantity}"
        }
    } else {
        null
    }

    fun getUnit(): Int? = ingredient.unit.res

    fun hasConnector(): Boolean = when (ingredient.unit) {
        Ingredient.Unit.NONE -> false
        else -> true
    }

    fun getName(): String = ingredient.name

}