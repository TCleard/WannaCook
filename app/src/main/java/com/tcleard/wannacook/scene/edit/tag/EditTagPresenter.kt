package com.tcleard.wannacook.scene.edit.tag

import com.tcleard.wannacook.core.extension.equalsQuery
import com.tcleard.wannacook.core.extension.matchQuery
import com.tcleard.wannacook.core.extension.rx.onIoToMain
import com.tcleard.wannacook.core.extension.rx.sub
import com.tcleard.wannacook.core.extension.unaccent
import com.tcleard.wannacook.core.model.Tag
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.core.service.ATagService
import com.tcleard.wannacook.scene.edit.AEditRecipeFragment
import com.tcleard.wannacook.scene.edit.tag.searchAdapter.vm.SearchTagViewModel
import com.tcleard.wannacook.scene.edit.tag.adapter.vm.EditTagViewModel
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
            view?.showSearchLoading()
            tagQuery = tagService.getRemoteTags(query)
                    .onIoToMain()
                    .sub(onSuccess = {
                        removeNonMatchingTags()
                        val matchingTags = it.items.filter { it.name.matchQuery(this.query) }.toMutableList()
                        if (matchingTags.none { it.name.equalsQuery(this.query)  }) {
                            matchingTags.add(0, Tag.builder()
                                    .name(this.query.toLowerCase().unaccent())
                                    .build())
                        }
                        searchedTags.clear()
                        searchedTags.addAll(matchingTags)
                        view?.showSearchTags(searchedTags.map { SearchTagViewModel(it) })
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
            view?.addSelectedTag(EditTagViewModel(tag, true))
            checkValidity()
        }
    }

    fun onRemoveClicked(viewModel: EditTagViewModel) {
        selectedTags.remove(viewModel.model)
        view?.removeSelectedTag(viewModel)
        checkValidity()
    }

    override fun onSelected() {
        view?.showSelectedTags(selectedTags.map { EditTagViewModel(it, true) })
        checkValidity()
    }

    private fun checkValidity() {
        view?.setState(selectedTags.isNotEmpty())
    }

    interface RecipeTagView : IView {

        fun setState(state: Boolean)

        fun showSelectedTags(viewModels: List<EditTagViewModel>)
        fun addSelectedTag(viewModel: EditTagViewModel)
        fun removeSelectedTag(viewModel: EditTagViewModel)

        fun showSearchTags(viewModels: List<SearchTagViewModel>)
        fun removeSearchTags(viewModels: List<SearchTagViewModel>)
        fun removeSearchTags()
        fun showSearchLoading()
        fun resetSearchQuery()

        fun showQueryError(error: Boolean)

    }

}