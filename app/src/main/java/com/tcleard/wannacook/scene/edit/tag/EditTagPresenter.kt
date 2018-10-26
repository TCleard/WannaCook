package com.tcleard.wannacook.scene.edit.tag

import com.tcleard.wannacook.core.extension.matchQuery
import com.tcleard.wannacook.core.extension.rx.onIoToMain
import com.tcleard.wannacook.core.extension.rx.sub
import com.tcleard.wannacook.core.model.Tag
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.core.service.ATagService
import com.tcleard.wannacook.scene.edit.AEditRecipeFragment
import com.tcleard.wannacook.scene.edit.tag.adapter.vm.SearchTagViewModel
import com.tcleard.wannacook.ui.adapter.tag.vm.TagViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class EditTagPresenter @Inject constructor(
        private val tagService: ATagService
) : AEditRecipeFragment.Presenter<EditTagPresenter.RecipeTagView>() {

    private val selectedTags = arrayListOf<Tag>()
    private val searchedTags = arrayListOf<Tag>()

    private var query: String = ""
    private var tagQuery: Disposable? = null

    fun searchTags(query: String) {
        this.query = query
        if (query.length >= 3) {
            view?.showQueryError(false)
            removeNonMatchingTags()
            if (searchedTags.isEmpty()) {
                view?.showSearchLoading()
            }
            tagQuery = tagService.getRemoteTags(query)
                    .onIoToMain()
                    .sub(onSuccess = {
                        removeNonMatchingTags()
                        if (searchedTags.isEmpty()) {
                            searchedTags.addAll(it.items)
                            view?.showSearchTags(searchedTags.map { SearchTagViewModel(it) })
                        } else {
                            view?.addSearchTags(it.items.filter { !searchedTags.contains(it) }
                                    .map { SearchTagViewModel(it) })
                            searchedTags.clear()
                            searchedTags.addAll(it.items)
                        }
                    })
        } else {
            tagQuery?.dispose()
            searchedTags.clear()
            view?.removeSearchTags()
            view?.showQueryError(true)
        }
    }

    private fun removeNonMatchingTags() {
        val tagsToKeep = searchedTags.filter { it.name.matchQuery(query) }
        val tagsToRemove = searchedTags.filter { !tagsToKeep.contains(it) }
        view?.removeSearchTags(tagsToRemove.map { SearchTagViewModel(it) })
        searchedTags.clear()
        searchedTags.addAll(tagsToKeep)
    }

    fun onTagClicked(viewModel: SearchTagViewModel) {
        val tag = viewModel.model
        if (!selectedTags.contains(tag)) {
            view?.resetSearchQuery()
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

        fun showSearchTags(viewModels: List<SearchTagViewModel>)
        fun addSearchTags(viewModels: List<SearchTagViewModel>)
        fun removeSearchTags(viewModels: List<SearchTagViewModel>)
        fun removeSearchTags()
        fun showSearchLoading()
        fun resetSearchQuery()

        fun showQueryError(error: Boolean)

    }

}