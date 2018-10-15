package com.tcleard.wannacook.scene.recipe.ingredient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.recipe.ARecipeFragment
import kotlinx.android.synthetic.main.fragment_ingredient.*

class IngredientFragment : ARecipeFragment<IngredientPresenter>(), IngredientPresenter.IngredientView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerIngredientComponent.builder()
                .appComponent(appComponent)
                .ingredientModule(IngredientModule())
                .build()
                .inject(this)

        presenter.attach(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_ingredient, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}