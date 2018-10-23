package com.tcleard.wannacook.scene.edit.tag

import com.tcleard.wannacook.core.extension.rx.onIoToMain
import com.tcleard.wannacook.core.extension.rx.sub
import com.tcleard.wannacook.core.model.Tag
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.core.service.ATagService
import com.tcleard.wannacook.scene.edit.AEditRecipeFragment
import com.tcleard.wannacook.ui.adapter.tag.vm.TagViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class RecipeTagPresenter @Inject constructor(
        private val tagService: ATagService
) : AEditRecipeFragment.Presenter<RecipeTagPresenter.RecipeTagView>() {

    private val selectedTags = arrayListOf<Tag>()
    private val searchedTags = arrayListOf<Tag>()

    private var tagQuery: Disposable? = null

    fun searchTags(query: String) {
        tagQuery?.dispose()
        if (query.length >= 3) {
            view?.showQueryError(false)
            val keptTags = searchedTags.filter { it.name.contains(query) }
            searchedTags.clear()
            searchedTags.addAll(keptTags)
            view?.showKeptSearchTags(searchedTags.map { TagViewModel(it, false) })
            tagQuery = tagService.getRemoteTags(query)
                    .onIoToMain()
                    .sub(onSuccess = {
                        searchedTags.clear()
                        searchedTags.addAll(it.items)
                        view?.showSearchTags(searchedTags.map { TagViewModel(it, false) })
                    })
        } else {
            searchedTags.clear()
            view?.removeSearchTags()
            view?.showQueryError(true)
        }
    }

    fun onTagClicked(viewModel: TagViewModel) {
        val tag = viewModel.model
        if (!selectedTags.contains(tag)) {
            selectedTags.add(tag)
            view?.addSelectedTag(TagViewModel(tag, true))
            checkValidity()
        }
    }

    fun onRemoveClicked(viewModel: TagViewModel) {
        selectedTags.remove(viewModel.model)
        view?.removeSelectedTag(viewModel)
        checkValidity()
    }

    override fun onSelected() {
        view?.showSelectedTags(selectedTags.map { TagViewModel(it, true) })
        checkValidity()
    }

    private fun checkValidity() {
        view?.setState(selectedTags.isNotEmpty())
    }

    interface RecipeTagView : IView {

        fun setState(state: Boolean)

        fun showSelectedTags(viewModels: List<TagViewModel>)
        fun addSelectedTag(viewModel: TagViewModel)
        fun removeSelectedTag(viewModel: TagViewModel)

        fun showSearchTags(viewModels: List<TagViewModel>)
        fun showKeptSearchTags(viewModels: List<TagViewModel>)

        fun removeSearchTags()
        fun showQueryError(error: Boolean)

    }

}