package com.tcleard.wannacook.scene.ingredient

import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.extension.isWhole
import com.tcleard.wannacook.core.model.Ingredient
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView
import javax.inject.Inject

class IngredientPresenter @Inject constructor(

) : APresenter<IngredientPresenter.IngredientView>() {

    private lateinit var ingredient: Ingredient

    private var name = ""
    private var unit = Ingredient.Unit.NONE
    private var quantity: Double = 0.0

    fun init(count: Int, ingredient: Ingredient?) {
        if (ingredient != null) {
            view?.showTitle(R.string.ingredientUpdate)
            name = ingredient.name
            unit = ingredient.unit
            quantity = ingredient.quantity
        } else {
            this.ingredient = Ingredient()
            view?.showTitle(R.string.ingredientNew)
        }
        view?.showCount(count)
        view?.showName(name)
        view?.showUnit(unit)
        view?.showQuantity(if (quantity > 0) {
            if (quantity.isWhole()) {
                quantity.toInt().toString()
            } else {
                quantity.toString()
            }
        } else {
            ""
        })
        this.ingredient = ingredient ?: Ingredient()
    }

    fun onBackPressed() {
        view?.showQuitPopup()
    }

    fun onNameChanged(name: String) {
        this.name = name
    }

    fun onUnitSelected(unit: Ingredient.Unit) {
        this.unit = unit
    }

    fun onQuantityChanged(quantity: Double) {
        this.quantity = quantity
    }

    fun onValidateClicked() {
        ingredient.name = name
        ingredient.unit = unit
        ingredient.quantity = quantity
        view?.quitWithIngredient(ingredient)
    }

    interface IngredientView : IView {

        fun showTitle(titleRes: Int)

        fun showName(name: String)
        fun showUnit(unit: Ingredient.Unit)
        fun showQuantity(quantity: String)
        fun showCount(count: Int)

        fun showQuitPopup()

        fun quitWithIngredient(ingredient: Ingredient)

    }

}