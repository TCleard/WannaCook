package com.tcleard.wannacook.scene.edit.ingredient

import com.tcleard.wannacook.core.model.Ingredient
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.scene.edit.AEditRecipeFragment
import com.tcleard.wannacook.scene.edit.ingredient.adapter.vm.EditIngredientViewModel
import javax.inject.Inject

class EditIngredientPresenter @Inject constructor(

) : AEditRecipeFragment.Presenter<EditIngredientPresenter.RecipeIngredientView>() {

    private var count = 4

    private val ingredients = arrayListOf<Ingredient>()

    private var editedIngredient: Ingredient? = null

    override fun onSelected() {
        showCount()
        view?.showIngredients(ingredients.map { EditIngredientViewModel(it) })
        checkValidity()
    }

    private fun checkValidity() {
        view?.setState(ingredients.isNotEmpty())
    }

    fun onPlusClicked() {
        count++
        showCount()
    }

    fun onMinusClicked() {
        if (count > 1) {
            count--
            showCount()
        }
    }

    fun onPlusLongClicked() {
        count += 10
        showCount()
    }

    fun onMinusLongClicked() {
        if (count > 10) {
            count -= 10
        } else {
            count = 1
        }
        showCount()
    }

    private fun showCount() {
        view?.showCount(count)
        view?.showMinusEnabled(count > 1)
    }

    fun onAddClicked() {
        editedIngredient = null
        view?.goToAddIngredient(count)
    }

    fun onEditClicked(viewModel: EditIngredientViewModel) {
        editedIngredient = viewModel.model
        view?.goToEditIngredient(viewModel.model, count)
    }

    fun onDeleteClicked(viewModel: EditIngredientViewModel) {
        ingredients.remove(viewModel.model)
        view?.removeIngredient(viewModel)
        checkValidity()
    }

    fun updateIngredient(ingredient: Ingredient) {
        editedIngredient?.let {
            ingredients.remove(it)
            view?.removeIngredient(EditIngredientViewModel(it))
            editedIngredient = null
        }
        ingredients.add(ingredient)
        view?.addIngredient(EditIngredientViewModel(ingredient))
        checkValidity()
    }

    interface RecipeIngredientView : IView {

        fun setState(state: Boolean)

        fun showCount(count: Int)
        fun showMinusEnabled(enable: Boolean)

        fun showIngredients(viewModels: List<EditIngredientViewModel>)
        fun addIngredient(viewModel: EditIngredientViewModel)
        fun removeIngredient(viewModel: EditIngredientViewModel)

        fun goToAddIngredient(count: Int)
        fun goToEditIngredient(ingredient: Ingredient, count: Int)

    }

}