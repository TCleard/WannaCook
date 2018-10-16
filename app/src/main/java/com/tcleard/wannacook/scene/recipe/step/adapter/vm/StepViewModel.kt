package com.tcleard.wannacook.scene.recipe.step.adapter.vm

import com.tcleard.wannacook.core.model.Step
import com.tcleard.wannacook.ui.adapter.AViewModel

class StepViewModel(
        private val position: Int,
        step: Step
) : AViewModel<Step>(step) {

    fun getPosition(): Int = position

    fun getStep(): String = model.text

}