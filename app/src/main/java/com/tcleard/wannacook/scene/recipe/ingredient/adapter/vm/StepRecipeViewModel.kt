package com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm

import com.tcleard.wannacook.core.model.Step

class StepRecipeViewModel(
        private val position: Int,
        private val step: Step
) : IRecipeViewModel {

    fun getPosition(): Int = position

    fun getStep(): String = step.text

}