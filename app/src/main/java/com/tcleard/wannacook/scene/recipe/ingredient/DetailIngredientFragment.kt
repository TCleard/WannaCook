package com.tcleard.wannacook.scene.recipe.ingredient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.recipe.ADetailRecipeFragment
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.DetailIngredientAdapter
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.DetailIngredientViewModel
import kotlinx.android.synthetic.main.fragment_detail_ingredient.*
import javax.inject.Inject

class DetailIngredientFragment : ADetailRecipeFragment<DetailIngredientPresenter>(), DetailIngredientPresenter.IngredientView, View.OnClickListener {

    @Inject
    lateinit var adapter: DetailIngredientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerDetailIngredientComponent.builder()
                .appComponent(appComponent)
                .detailIngredientModule(DetailIngredientModule())
                .build()
                .inject(this)

        presenter.attach(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_detail_ingredient, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.setRecipe(recipe)

        detailIngredientList.layoutManager = adapter.generateLayoutManager(requireContext())
        detailIngredientList.adapter = adapter

        detailIngredientMinus.setOnClickListener(this)
        detailIngredientPlus.setOnClickListener(this)

    }

    /** IngredientView **/

    override fun showIngredients(ingredients: List<DetailIngredientViewModel>) {
        adapter.setItems(ingredients)
    }

    override fun showCount(count: Int) {
        detailIngredientCount.text = "$count"
        detailIngredientPeopleText.text = resources.getQuantityString(R.plurals.peopleCount, count)
    }

    override fun showMinusEnabled(enabled: Boolean) {
        detailIngredientMinus.isEnabled = enabled
    }

    /** Listeners **/

    override fun onClick(v: View?) {
        when (v) {
            detailIngredientMinus -> presenter.onMinusClicked()
            detailIngredientPlus -> presenter.onPlusClicked()
        }
    }

}