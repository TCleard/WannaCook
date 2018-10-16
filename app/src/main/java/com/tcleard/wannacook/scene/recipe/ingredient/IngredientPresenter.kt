package com.tcleard.wannacook.scene.recipe.ingredient

import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.IngredientViewModel
import javax.inject.Inject

class IngredientPresenter @Inject constructor(

) : APresenter<IngredientPresenter.IngredientView>() {

    private lateinit var recipe: Recipe

    private var count = 1

    fun setRecipe(recipe: Recipe) {
        this.recipe = recipe
        this.count = recipe.count
        updateCount()
    }

    fun onMinusClicked() {
        count--
        updateCount()
    }

    fun onPlusClicked() {
        count++
        updateCount()
    }

    private fun updateCount() {
        view?.showCount(count)
        view?.showMinusEnabled(count > 1)
        view?.showIngredients(recipe.ingredients.map { IngredientViewModel(recipe.count, count, it) })
    }

    interface IngredientView : IView {

        fun showIngredients(ingredients: List<IngredientViewModel>)

        fun showMinusEnabled(enabled: Boolean)
        fun showCount(count: Int)

    }

}