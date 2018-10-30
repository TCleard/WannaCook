package com.tcleard.wannacook.scene.edit.title

import com.tcleard.wannacook.core.manager.IKeyboardManager
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.scene.edit.AEditRecipeFragment
import javax.inject.Inject

class EditTitlePresenter @Inject constructor(
        private val keyboardManager: IKeyboardManager
) : AEditRecipeFragment.Presenter<EditTitlePresenter.RecipeTitleView>() {

    private var title = ""
    private var color: Int? = null

    override fun onSelected() {
        view?.showTitle(title)
        view?.showColor(color)
        checkValidity()
    }

    fun setTitle(title: String) {
        this.title = title
        checkValidity()
    }

    fun setColor(color: Int) {
        this.color = color
        view?.showColor(color)
    }

    fun onColorClicked() {
        keyboardManager.closeKeyboard()
        view?.goToColorPicker(color)
    }

    private fun checkValidity() {
        view?.setState(title.length >= 5)
    }

    interface RecipeTitleView : IView {

        fun setState(state: Boolean)

        fun showTitle(title: String)
        fun showColor(color: Int?)

        fun goToColorPicker(color: Int?)

    }

}