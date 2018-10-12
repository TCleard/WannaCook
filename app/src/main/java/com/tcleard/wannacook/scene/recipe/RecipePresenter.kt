package com.tcleard.wannacook.scene.recipe

import javax.inject.Inject
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView

class RecipePresenter @Inject constructor(

) : APresenter<RecipePresenter.RecipeView>() {

    interface RecipeView : IView {

    }

}