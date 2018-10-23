package com.tcleard.wannacook.scene.main

import android.widget.ImageView
import com.tcleard.wannacook.core.extension.rx.onIoToMain
import com.tcleard.wannacook.core.extension.rx.sub
import com.tcleard.wannacook.core.manager.ITimeManager
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.core.service.ARecipeService
import com.tcleard.wannacook.scene.main.adapter.vm.RecipeViewModel
import javax.inject.Inject

class MainPresenter @Inject constructor(
        private val timeManager: ITimeManager,
        private val recipeService: ARecipeService
) : APresenter<MainPresenter.MainView>() {

    private val recipes = arrayListOf<Recipe>()

    override fun attach(view: MainView) {
        super.attach(view)

        getLocalRecipes()
        refreshRecipes()
    }

    private fun getLocalRecipes() {
        compositeDisposable.add(
                recipeService.getLocalRecipes()
                        .onIoToMain()
                        .sub(onSuccess = {
                            if (it.isNotEmpty()) {
                                showRecipes(it)
                            }
                        })
        )
    }

    fun refreshRecipes() {
        view?.showLoading(true)
        compositeDisposable.add(
                recipeService.getRemoteRecipes()
                        .onIoToMain()
                        .doAfterSuccess { view?.showLoading(false) }
                        .sub(
                                onSuccess = {
                                    showRecipes(it.items)
                                },
                                onError = { e ->
                                    if (recipes.isEmpty()) {
                                        showRecipes(listOf())
                                    }
                                    // TODO('Handle error')
                                }
                        )
        )
    }

    private fun showRecipes(recipes: List<Recipe>) {
        this.recipes.clear()
        this.recipes.addAll(recipes)
        view?.showRecipes(recipes.map {
            RecipeViewModel(it, timeManager) { recipe, imageView ->
                view?.goToRecipe(recipe, imageView)
            }
        })
    }

    interface MainView : IView {

        fun showRecipes(viewModels: List<RecipeViewModel>)

        fun showLoading(loading: Boolean)

        fun goToRecipe(recipe: Recipe, imageView: ImageView)

    }

}