package com.tcleard.wannacook.scene.main

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.ImageView
import android.widget.TextView
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.scene.main.adapter.RecipeAdapter
import com.tcleard.wannacook.scene.main.adapter.vm.RecipeViewModel
import com.tcleard.wannacook.scene.recipe.RecipeActivity
import com.tcleard.wannacook.ui.controller.AActivity
import com.tcleard.wannacook.ui.decoration.ComponentDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class MainActivity : AActivity<MainPresenter>(), MainPresenter.MainView {

    @Inject
    lateinit var adapter: RecipeAdapter

    private var baseToolbarY: Float by Delegates.notNull()
    private var baseFabY: Float by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbarLayout.toolbar)

        mainRoot.post {
            baseToolbarY = mainToolbarLayout.y
            baseFabY = mainAdd.y
        }

        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(MainModule())
                .build()
                .inject(this)

        presenter.attach(this)

        mainList.addItemDecoration(ComponentDecoration(this, ComponentDecoration.Component.TOOLBAR, ComponentDecoration.Component.FAB))
        mainList.layoutManager = object : GridLayoutManager(this, adapter.getSpanCount()) {

            override fun getSpanSizeLookup(): SpanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int = adapter.getSpanSize(position)
            }

        }
        mainList.adapter = adapter

    }

    /** MainView **/

    override fun showRecipes(viewModels: List<RecipeViewModel>) {
        adapter.setItems(viewModels)
    }

    override fun goToRecipe(recipe: Recipe, imageView: ImageView, textView: TextView) {
        addExitListener(onExit = {
            mainToolbarLayout.animate()
                    .y(-mainToolbarLayout.height.toFloat())
                    .start()
            mainAdd.animate()
                    .y(mainRoot.bottom.toFloat())
                    .start()
        }, onReenter = {
            mainToolbarLayout.animate()
                    .y(baseToolbarY)
                    .start()
            mainAdd.animate()
                    .y(baseFabY)
                    .start()
        })
        RecipeActivity.builder(this)
                .with(recipe)
                .addSharedElement(imageView, R.string.sharedRecipeImage)
                .addSharedElement(textView, R.string.sharedRecipeName)
                .start()
    }

}