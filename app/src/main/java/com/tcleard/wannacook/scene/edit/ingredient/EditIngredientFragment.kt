package com.tcleard.wannacook.scene.edit.ingredient

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.model.Ingredient
import com.tcleard.wannacook.scene.edit.AEditRecipeFragment
import com.tcleard.wannacook.scene.edit.ingredient.adapter.EditIngredientAdapter
import com.tcleard.wannacook.scene.edit.ingredient.adapter.vm.EditIngredientViewModel
import com.tcleard.wannacook.scene.ingredient.IngredientActivity
import com.tcleard.wannacook.ui.decoration.ColorSeparator
import kotlinx.android.synthetic.main.fragment_edit_ingredient.*
import javax.inject.Inject

class EditIngredientFragment : AEditRecipeFragment<EditIngredientPresenter>(), EditIngredientPresenter.RecipeIngredientView, View.OnClickListener, View.OnLongClickListener, EditIngredientAdapter.OnItemClickListener {

    private val REQUEST_INGREDIENT = 1

    @Inject
    lateinit var adapter: EditIngredientAdapter

    override fun onFirstCreate() {
        super.onFirstCreate()
        DaggerEditIngredientComponent.builder()
                .appComponent(appComponent)
                .editIngredientModule(EditIngredientModule(this))
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_edit_ingredient, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attach(this)

        editIngredientList.addItemDecoration(ColorSeparator.builder(requireContext())
                .setPaddingRes(R.dimen.paddingMedium)
                .build())
        editIngredientList.layoutManager = LinearLayoutManager(requireContext())
        editIngredientList.adapter = adapter

        editIngredientAdd.setOnClickListener(this)
        editIngredientPlus.setOnClickListener(this)
        editIngredientMinus.setOnClickListener(this)
        editIngredientPlus.setOnLongClickListener(this)
        editIngredientMinus.setOnLongClickListener(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_INGREDIENT) {
            if (resultCode == Activity.RESULT_OK) {
                IngredientActivity.retrieveIngredient(data)?.let { ingredient ->
                    presenter.updateIngredient(ingredient)
                }
            }
        }
    }

    /** RecipeIngredientView **/

    override fun setState(state: Boolean) {
        statePublisher.onNext(state)
    }

    override fun showCount(count: Int) {
        editIngredientCount.text = "$count"
    }

    override fun showMinusEnabled(enable: Boolean) {
        editIngredientMinus.isEnabled = enable
    }

    override fun showIngredients(viewModels: List<EditIngredientViewModel>) {
        adapter.setItems(viewModels)
    }

    override fun addIngredient(viewModel: EditIngredientViewModel) {
        adapter.add(viewModel)
    }

    override fun removeIngredient(viewModel: EditIngredientViewModel) {
        adapter.remove(viewModel)
    }

    override fun goToAddIngredient(count: Int) {
        IngredientActivity.builder(this)
                .with(count)
                .startForResult(REQUEST_INGREDIENT)
    }

    override fun goToEditIngredient(ingredient: Ingredient, count: Int) {
        IngredientActivity.builder(this)
                .with(ingredient)
                .with(count)
                .startForResult(REQUEST_INGREDIENT)
    }

    /** Listeners **/

    override fun onClick(v: View?) {
        when (v) {
            editIngredientAdd -> presenter.onAddClicked()
            editIngredientPlus -> presenter.onPlusClicked()
            editIngredientMinus -> presenter.onMinusClicked()
        }
    }

    override fun onLongClick(v: View?): Boolean {
        when (v) {
            editIngredientPlus -> presenter.onPlusLongClicked()
            editIngredientMinus -> presenter.onMinusLongClicked()
        }
        return true
    }

    override fun onEditClicked(viewModel: EditIngredientViewModel) {
        presenter.onEditClicked(viewModel)
    }

    override fun onDeleteClicked(viewModel: EditIngredientViewModel) {
        presenter.onDeleteClicked(viewModel)
    }
}