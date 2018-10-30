package com.tcleard.wannacook.scene.recipe

import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.manager.ITimeManager
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.scene.recipe.ingredient.DetailIngredientFragment
import com.tcleard.wannacook.scene.recipe.step.DetailStepFragment
import javax.inject.Inject

class DetailRecipePresenter @Inject constructor(
        private val timeManager: ITimeManager
) : APresenter<DetailRecipePresenter.RecipeView>() {

    fun setRecipe(recipe: Recipe) {

        view?.showImage(recipe.imageUrl)
        view?.showRecipeColor(recipe.color)
        view?.showName(recipe.name)

        view?.showPreparationTime(recipe.preparationTime?.let { timeManager.getDuration(it) })
        view?.showCookingTime(recipe.cookingTime?.let { timeManager.getDuration(it) })
        view?.showLevel(R.string.medium)

        val fragments = arrayListOf<ADetailRecipeFragment<*>>()
        if (recipe.ingredients.isNotEmpty()) {
            val ingredientFragment = DetailIngredientFragment()
            ingredientFragment.recipe = recipe
            fragments.add(ingredientFragment)
        }
        if (recipe.steps.isNotEmpty()) {
            val stepFragment = DetailStepFragment()
            stepFragment.recipe = recipe
            fragments.add(stepFragment)
        }

        view?.showFragments(fragments)

    }

    interface RecipeView : IView {

        fun showImage(imageUrl: String)
        fun showRecipeColor(color: Int?)
        fun showName(name: String)

        fun showPreparationTime(preparation: String?)
        fun showCookingTime(cooking: String?)
        fun showLevel(level: Int)

        fun showFragments(fragments: List<ADetailRecipeFragment<*>>)

    }

}