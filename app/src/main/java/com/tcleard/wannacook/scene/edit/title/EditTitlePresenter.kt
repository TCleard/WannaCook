package com.tcleard.wannacook.scene.edit.title

import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.scene.edit.AEditRecipeFragment
import javax.inject.Inject

class EditTitlePresenter @Inject constructor(

) : AEditRecipeFragment.Presenter<EditTitlePresenter.RecipeTitleView>() {

    private var title = ""

    override fun onSelected() {
        view?.showTitle(title)
        checkValidity()
    }

    fun setTitle(title: String) {
        this.title = title
        checkValidity()
    }

    private fun checkValidity() {
        view?.setState(title.length >= 5)
    }

    interface RecipeTitleView : IView {

        fun setState(state: Boolean)

        fun showTitle(title: String)

    }

}