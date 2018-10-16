package com.tcleard.wannacook.scene.recipe.ingredient

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.recipe.ARecipeFragment
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.IngredientAdapter
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.IngredientViewModel
import kotlinx.android.synthetic.main.fragment_ingredient.*
import javax.inject.Inject

class IngredientFragment : ARecipeFragment<IngredientPresenter>(), IngredientPresenter.IngredientView, View.OnClickListener {

    @Inject
    lateinit var adapter: IngredientAdapter

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

        presenter.setRecipe(recipe)

        ingredientList.layoutManager = LinearLayoutManager(requireContext())
        ingredientList.adapter = adapter

        ingredientPeopleMinus.setOnClickListener(this)
        ingredientPeoplePlus.setOnClickListener(this)

    }

    /** IngredientView **/

    override fun showIngredients(ingredients: List<IngredientViewModel>) {
        adapter.setItems(ingredients)
    }

    override fun showCount(count: Int) {
        ingredientPeopleCount.text = "$count"
        ingredientPeopleText.text = resources.getQuantityString(R.plurals.peopleCount, count)
    }

    override fun showMinusEnabled(enabled: Boolean) {
        ingredientPeopleMinus.isEnabled = enabled
    }

    /** Listeners **/

    override fun onClick(v: View?) {
        when (v) {
            ingredientPeopleMinus -> presenter.onMinusClicked()
            ingredientPeoplePlus -> presenter.onPlusClicked()
        }
    }

}