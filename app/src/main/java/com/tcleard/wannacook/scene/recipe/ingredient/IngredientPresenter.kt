package com.tcleard.wannacook.scene.recipe.ingredient

import javax.inject.Inject
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView

class IngredientPresenter @Inject constructor(

) : APresenter<IngredientPresenter.IngredientView>() {

    interface IngredientView : IView {

    }

}