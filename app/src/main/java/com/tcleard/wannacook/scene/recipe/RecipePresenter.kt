package com.tcleard.wannacook.scene.recipe

import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.scene.recipe.ingredient.IngredientFragment
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.HeaderRecipeViewModel
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.IRecipeViewModel
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.IngredientRecipeViewModel
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.StepRecipeViewModel
import javax.inject.Inject

class RecipePresenter @Inject constructor(

) : APresenter<RecipePresenter.RecipeView>() {

    fun setRecipe(recipe: Recipe) {
        view?.showImage(recipe.imageUrl)
        view?.showFabColor(recipe.type.color)
        view?.showName(recipe.name)

        val fragments = arrayListOf<ARecipeFragment<*>>()
        if (recipe.ingredients.isNotEmpty()) {
            val ingredientFragment = IngredientFragment()
            ingredientFragment.recipe = recipe
            fragments.add(ingredientFragment)
        }

        view?.showFragments(fragments)

        val ingredientsViewModels = recipe.ingredients.map { IngredientRecipeViewModel(it) }
        var stepCount = 0
        val stepViewModels = recipe.steps.map {
            stepCount++
            StepRecipeViewModel(stepCount, it)
        }

        val viewModels = arrayListOf<IRecipeViewModel>()

        if (ingredientsViewModels.isNotEmpty()) {
            viewModels.add(HeaderRecipeViewModel(R.string.recipeIngredients, R.mipmap.ingredients))
            viewModels.addAll(ingredientsViewModels)
        }
        if (stepViewModels.isNotEmpty()) {
            viewModels.add(HeaderRecipeViewModel(R.string.recipeSteps, R.mipmap.board))
            viewModels.addAll(stepViewModels)
        }

    }

    interface RecipeView : IView {

        fun showImage(imageUrl: String)
        fun showFabColor(color: Int?)
        fun showName(name: String)

        fun showFragments(fragments: List<ARecipeFragment<*>>)

    }

}