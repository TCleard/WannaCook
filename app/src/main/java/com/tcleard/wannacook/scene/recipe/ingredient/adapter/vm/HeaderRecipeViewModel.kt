package com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm

class HeaderRecipeViewModel(
        private val textRes: Int,
        private val iconRes: Int
) : IRecipeViewModel {

    fun getTextRes(): Int = textRes

    fun getIconRes() : Int= iconRes

}