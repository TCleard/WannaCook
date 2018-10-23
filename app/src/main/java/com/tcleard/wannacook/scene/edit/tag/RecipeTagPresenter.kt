package com.tcleard.wannacook.scene.edit.tag

import com.tcleard.wannacook.core.model.Tag
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.scene.edit.AEditRecipeFragment
import com.tcleard.wannacook.scene.edit.tag.adapter.vm.RecipeTagViewModel
import javax.inject.Inject

class RecipeTagPresenter @Inject constructor(

) : AEditRecipeFragment.Presenter<RecipeTagPresenter.RecipeTagView>() {

    private val tags = arrayListOf<Tag>()

    override fun onSelected() {
        view?.showTags(tags.map { RecipeTagViewModel(it, true) })
        checkValidity()
    }

    private fun checkValidity() {
        view?.setState(tags.isNotEmpty())
    }

    interface RecipeTagView : IView {

        fun setState(state: Boolean)

        fun showTags(viewModels: List<RecipeTagViewModel>)

    }

}