package com.tcleard.wannacook.scene.ingredient.adapter

import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.model.Ingredient

class IngredientUnitItem(
        val unit: Ingredient.Unit
) {

    fun getNameRes(): Int = unit.res ?: R.string.unitNone

}