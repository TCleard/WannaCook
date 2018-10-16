package com.tcleard.wannacook.scene.recipe.step

import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.scene.recipe.step.adapter.vm.StepViewModel
import javax.inject.Inject

class StepPresenter @Inject constructor(

) : APresenter<StepPresenter.StepView>() {

    private lateinit var recipe: Recipe

    fun setRecipe(recipe: Recipe) {
        this.recipe = recipe
        var stepCount = 0
        view?.showSteps(recipe.steps.map {
            stepCount++
            StepViewModel(stepCount, it)
        })
    }

    interface StepView : IView {

        fun showSteps(steps: List<StepViewModel>)

    }

}