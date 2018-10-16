package com.tcleard.wannacook.scene.recipe

import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.scene.recipe.ingredient.IngredientFragment
import com.tcleard.wannacook.scene.recipe.step.StepFragment
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
        if (recipe.steps.isNotEmpty()) {
            val stepFragment = StepFragment()
            stepFragment.recipe = recipe
            fragments.add(stepFragment)
        }

        view?.showFragments(fragments)

    }

    interface RecipeView : IView {

        fun showImage(imageUrl: String)
        fun showFabColor(color: Int?)
        fun showName(name: String)

        fun showFragments(fragments: List<ARecipeFragment<*>>)

    }

}