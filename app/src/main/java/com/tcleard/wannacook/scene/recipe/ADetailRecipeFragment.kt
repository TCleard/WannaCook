package com.tcleard.wannacook.scene.recipe

import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.ui.controller.AFragment

abstract class ADetailRecipeFragment<P : APresenter<*>> : AFragment<P>() {

    lateinit var recipe: Recipe

}