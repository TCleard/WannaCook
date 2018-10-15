package com.tcleard.wannacook.scene.recipe

import com.tcleard.wannacook.core.model.Recipe
import javax.inject.Inject
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView

class RecipePresenter @Inject constructor(

) : APresenter<RecipePresenter.RecipeView>() {

    fun setRecipe(recipe: Recipe) {
        view?.showImage(recipe.imageUrl)
        view?.showImageBackground(recipe.type.color)
        view?.showName(recipe.name)
    }

    interface RecipeView : IView {

        fun showImage(imageUrl : String)
        fun showImageBackground(color: Int?)
        fun showName(name: String)

    }

}