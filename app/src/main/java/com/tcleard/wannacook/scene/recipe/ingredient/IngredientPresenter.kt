package com.tcleard.wannacook.scene.recipe.ingredient

import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.IngredientViewModel
import javax.inject.Inject

class IngredientPresenter @Inject constructor(

) : APresenter<IngredientPresenter.IngredientView>() {

    private lateinit var recipe: Recipe

    fun setRecipe(recipe: Recipe) {
        this.recipe = recipe
        view?.showIngredients(recipe.ingredients.map { IngredientViewModel(it) })
    }

    interface IngredientView : IView {

        fun showIngredients(ingredients: List<IngredientViewModel>)

    }

}