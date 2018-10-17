package com.tcleard.wannacook.scene.edit

import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView
import javax.inject.Inject

class EditRecipePresenter @Inject constructor(

) : APresenter<EditRecipePresenter.EditView>() {

    private var recipe: Recipe? = null

    fun setRecipe(recipe: Recipe?) {
        this.recipe = recipe
        if (recipe != null) {
            view?.showTitle(R.string.editOld)
        } else {
            view?.showTitle(R.string.editNew)
        }
    }

    interface EditView : IView {

        fun showTitle(titleRes: Int)

    }

}