package com.tcleard.wannacook.scene.recipe

import android.content.Context
import android.os.Bundle
import com.tcleard.wannacook.R
import com.tcleard.wannacook.ui.controller.AActivity
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : AActivity<RecipePresenter>(), RecipePresenter.RecipeView {

    companion object {

        fun builder(context: Context): Builder = Builder(context)

        class Builder(context: Context) : AActivity.Builder(context) {
            override val activityClass = RecipeActivity::class.java
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        DaggerRecipeComponent.builder()
                .appComponent(appComponent)
                .recipeModule(RecipeModule())
                .build()
                .inject(this)

        presenter.attach(this)

    }

}