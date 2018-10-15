package com.tcleard.wannacook.scene.main

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import com.tcleard.wannacook.core.manager.ITimeManager
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.scene.main.adapter.vm.RecipeViewModel
import javax.inject.Inject

class MainPresenter @Inject constructor(
        private val timeManager: ITimeManager
) : APresenter<MainPresenter.MainView>() {

    override fun attach(view: MainView) {
        super.attach(view)

        val recipes = arrayListOf<Recipe>()

        val type1 = Recipe.Type()
        type1.name = "Dessert"
        type1.color = Color.parseColor("#E0750E")

        val recipe1 = Recipe()
        recipe1.type = type1
        recipe1.imageUrl = "https://cache.marieclaire.fr/data/photo/w1000_c17/cuisine/i13_ratrap_img1/36424602.jpg"
        recipe1.name = "Crêpes sucrées"
        recipe1.preparationTime = 1000 * 60 * 30
        recipes.add(recipe1)

        val type2 = Recipe.Type()
        type2.name = "Poisson"
        type2.color = Color.parseColor("#EF234E")

        val recipe2 = Recipe()
        recipe2.type = type2
        recipe2.name = "Saumon en papillote"
        recipe2.imageUrl = "https://cac.img.pmdstatic.net/fit/http.3A.2F.2Fprd2-bone-image.2Es3-website-eu-west-1.2Eamazonaws.2Ecom.2Fcac.2F2018.2F09.2F25.2F23711f94-b1e4-40f7-a589-a8ce0e0ac7a5.2Ejpeg/737x415/quality/80/crop-from/center/saumon-en-papillote.jpeg"
        recipe2.preparationTime = 1000 * 60 * 15
        recipe2.cookingTime = 1000 * 60 * 20
        recipes.add(recipe2)

        view.showRecipes(recipes.map {
            RecipeViewModel(it, timeManager) { recipe, imageView, textView ->
                this.view?.goToRecipe(recipe, imageView, textView)
            }
        })

    }

    interface MainView : IView {

        fun showRecipes(viewModels: List<RecipeViewModel>)

        fun goToRecipe(recipe: Recipe, imageView: ImageView, textView: TextView)

    }

}