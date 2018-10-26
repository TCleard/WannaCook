package com.tcleard.wannacook.scene.edit.ingredient.adapter.vm

import com.tcleard.wannacook.core.extension.isWhole
import com.tcleard.wannacook.core.model.Ingredient
import com.tcleard.wannacook.ui.adapter.AViewModel

class EditIngredientViewModel(
        ingredient: Ingredient
) : AViewModel<Ingredient>(ingredient) {

    fun getQuantity(): String? = if (model.quantity > 0) {
        val quantity = model.quantity
        if (quantity.isWhole() || model.unit == Ingredient.Unit.SP || model.unit == Ingredient.Unit.NONE) {
            "${Math.ceil(quantity).toInt()}"
        } else {
            "$quantity"
        }
    } else {
        null
    }

    fun getUnit(): Int? = model.unit.res

    fun hasConnector(): Boolean = when (model.unit) {
        Ingredient.Unit.NONE -> false
        else -> true
    }

    fun getName(): String = model.name

}