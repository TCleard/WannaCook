package com.tcleard.wannacook.scene.edit.tag

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexboxLayoutManager
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.extension.watch
import com.tcleard.wannacook.scene.edit.AEditRecipeFragment
import com.tcleard.wannacook.scene.edit.tag.adapter.SearchTagAdapter
import com.tcleard.wannacook.scene.edit.tag.adapter.vm.SearchTagViewModel
import com.tcleard.wannacook.ui.adapter.tag.TagAdapter
import com.tcleard.wannacook.ui.adapter.tag.vm.TagViewModel
import kotlinx.android.synthetic.main.fragment_edit_tag.*
import javax.inject.Inject

class EditTagFragment : AEditRecipeFragment<EditTagPresenter>(), EditTagPresenter.RecipeTagView, TagAdapter.OnItemClickListener, SearchTagAdapter.OnTagClickListener {

    @Inject
    lateinit var selectedTagAdapter: TagAdapter

    @Inject
    lateinit var searchTagAdapter: SearchTagAdapter

    override fun onFirstCreate() {
        super.onFirstCreate()
        DaggerEditTagComponent.builder()
                .appComponent(appComponent)
                .editTagModule(EditTagModule(this))
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_edit_tag, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attach(this)

        editTagField.watch(afterTextChanged = {
            presenter.searchTags(editTagField.text.toString())
        })

        editTagSelectedList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        editTagSelectedList.adapter = selectedTagAdapter

        editTagSearchList.layoutManager = FlexboxLayoutManager(requireContext())
        editTagSearchList.adapter = searchTagAdapter

    }

    /** RecipeTagView **/

    override fun setState(state: Boolean) {
        statePublisher.onNext(state)
    }

    override fun showSelectedTags(viewModels: List<TagViewModel>) {
        selectedTagAdapter.setItems(viewModels)
    }

    override fun addSelectedTag(viewModel: TagViewModel) {
        selectedTagAdapter.add(viewModel)
        editTagSelectedList.smoothScrollToPosition(selectedTagAdapter.itemCount - 1)
    }

    override fun removeSelectedTag(viewModel: TagViewModel) {
        selectedTagAdapter.remove(viewModel)
    }

    override fun showSearchTags(viewModels: List<SearchTagViewModel>) {
        searchTagAdapter.setItems(viewModels)
    }

    override fun addSearchTags(viewModels: List<SearchTagViewModel>) {
        searchTagAdapter.add(viewModels)
    }

    override fun removeSearchTags(viewModels: List<SearchTagViewModel>) {
        searchTagAdapter.remove { it.getId() in viewModels.map { it.getId() } }
    }

    override fun removeSearchTags() {
        searchTagAdapter.removeAll()
    }

    override fun showSearchLoading() {
        searchTagAdapter.notifyLoading()
    }

    override fun resetSearchQuery() {
        editTagField.setText("")
    }

    override fun showQueryError(error: Boolean) {
        if (error) {
            editTagFieldExtra.setTextColor(resources.getColor(R.color.red))
        } else {
            editTagFieldExtra.setTextColor(resources.getColor(R.color.light))
        }
    }

    /** Listeners **/

    override fun onTagClicked(viewModel: SearchTagViewModel) {
        presenter.onTagClicked(viewModel)
    }

    override fun onRemoveClicked(viewModel: TagViewModel) {
        presenter.onRemoveClicked(viewModel)
    }

}