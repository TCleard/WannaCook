package com.tcleard.wannacook.scene.main

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.main.adapter.RecipeAdapter
import com.tcleard.wannacook.scene.main.adapter.vm.RecipeViewModel
import com.tcleard.wannacook.ui.controller.AActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.view.*
import javax.inject.Inject

class MainActivity : AActivity<MainPresenter>(), MainPresenter.MainView, RecipeAdapter.OnClickListener {

    @Inject
    lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbarLayout.toolbar)

        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(MainModule(this))
                .build()
                .inject(this)

        presenter.attach(this)

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

    /** Listeners **/

    override fun onRecipeClicked(viewModel: RecipeViewModel) {
    }

}