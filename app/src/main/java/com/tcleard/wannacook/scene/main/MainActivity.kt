package com.tcleard.wannacook.scene.main

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.ImageView
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.scene.edit.EditRecipeActivity
import com.tcleard.wannacook.scene.main.adapter.MainAdapter
import com.tcleard.wannacook.scene.main.adapter.vm.RecipeViewModel
import com.tcleard.wannacook.scene.recipe.DetailRecipeActivity
import com.tcleard.wannacook.ui.controller.AActivity
import com.tcleard.wannacook.ui.decoration.ComponentDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class MainActivity : AActivity<MainPresenter>(), MainPresenter.MainView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var adapter: MainAdapter

    private var baseToolbarY: Float by Delegates.notNull()
    private var baseFabY: Float by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbarLayout.toolbar)

        mainRoot.post {
            baseToolbarY = mainToolbarLayout.y
            baseFabY = mainAdd.y
            mainPTR.setProgressViewOffset(false, baseToolbarY.toInt(), (mainToolbarLayout.y + (mainToolbarLayout.height * 2)).toInt())
        }

        mainPTR.setOnRefreshListener(this)

        mainAdd.setOnClickListener(this)

        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(MainModule())
                .build()
                .inject(this)

        presenter.attach(this)

        mainList.addItemDecoration(ComponentDecoration(this, ComponentDecoration.Component.TOOLBAR, ComponentDecoration.Component.FAB))
        mainList.layoutManager = adapter.generateLayoutManager(this)
        mainList.adapter = adapter

    }

    /** MainView **/

    override fun showRecipes(viewModels: List<RecipeViewModel>) {
        adapter.setItems(viewModels)
    }

    override fun showLoading(loading: Boolean) {
        mainPTR.isRefreshing = loading
    }

    override fun goToRecipe(recipe: Recipe, imageView: ImageView) {
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
        DetailRecipeActivity.builder(this)
                .with(recipe)
                .addSharedElement(imageView, R.string.sharedRecipeImage)
                .start()
    }

    /** Listeners **/

    override fun onClick(v: View?) {
        when (v) {
            mainAdd -> EditRecipeActivity.builder(this)
                    .start()
        }
    }

    override fun onRefresh() {
        presenter.refreshRecipes()
    }

}