package com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm

import com.tcleard.wannacook.core.extension.isWhole
import com.tcleard.wannacook.core.model.Ingredient
import com.tcleard.wannacook.ui.adapter.AViewModel

class DetailIngredientViewModel(
        private val baseCount: Int,
        private val editCount: Int,
        ingredient: Ingredient
) : AViewModel<Ingredient>(ingredient) {

    fun getQuantity(): String? = if (model.quantity > 0) {
        var quantity = model.quantity
        quantity *= (editCount.toDouble() / baseCount.toDouble())
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