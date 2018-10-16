package com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm

import com.tcleard.wannacook.core.extension.isWhole
import com.tcleard.wannacook.core.model.Ingredient
import com.tcleard.wannacook.ui.adapter.AViewModel

class IngredientViewModel(
        ingredient: Ingredient
) : AViewModel<Ingredient>(ingredient) {

    fun getQuantity(): String? = if (model.quantity > 0) {
        if (model.unit == Ingredient.Unit.NONE || model.quantity.isWhole()) {
            "${model.quantity.toInt()}"
        } else {
            "${model.quantity}"
        }
    } else {
        null
    }

    fun getUnit(): Int? = model.unit.res

    fun hasConnector(): Boolean = when (model.unit) {
        Ingredient.Unit.NONE -> false
        else -> true
    }

    fun getName(): String = model.name.decapitalize()

}