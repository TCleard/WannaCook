package com.tcleard.wannacook.scene.edit.tag

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexboxLayoutManager
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.edit.AEditRecipeFragment
import com.tcleard.wannacook.ui.adapter.tag.TagAdapter
import com.tcleard.wannacook.ui.adapter.tag.vm.TagViewModel
import kotlinx.android.synthetic.main.fragment_recipe_tag.*
import javax.inject.Inject
import javax.inject.Named

class RecipeTagFragment : AEditRecipeFragment<RecipeTagPresenter>(), RecipeTagPresenter.RecipeTagView, TagAdapter.OnItemClickListener {

    @field:[Inject Named("selected")]
    lateinit var selectedTagAdapter: TagAdapter

    @field:[Inject Named("search")]
    lateinit var searchTagAdapter: TagAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerRecipeTagComponent.builder()
                .appComponent(appComponent)
                .recipeTagModule(RecipeTagModule(this))
                .build()
                .inject(this)

        presenter.attach(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_recipe_tag, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeTagSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                presenter.searchTags(recipeTagSearch.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        recipeSelectedTagList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recipeSelectedTagList.adapter = selectedTagAdapter

        recipeSearchTagList.layoutManager = FlexboxLayoutManager(requireContext())
        recipeSearchTagList.adapter = searchTagAdapter

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
    }

    override fun removeSelectedTag(viewModel: TagViewModel) {
        selectedTagAdapter.remove(viewModel)
    }

    override fun showSearchTags(viewModels: List<TagViewModel>) {
        searchTagAdapter.setItems(viewModels)
    }

    override fun showKeptSearchTags(viewModels: List<TagViewModel>) {
        searchTagAdapter.remove { it.getId() !in viewModels.map { it.getId() } }
    }

    override fun removeSearchTags() {
        searchTagAdapter.removeAll()
    }

    override fun showQueryError(error: Boolean) {
        if (error) {
            recipeTagSearchExtra.setTextColor(resources.getColor(R.color.red))
        } else {
            recipeTagSearchExtra.setTextColor(resources.getColor(R.color.light))
        }
    }

    /** Listeners **/

    override fun onTagClicked(viewModel: TagViewModel) {
        presenter.onTagClicked(viewModel)
    }

    override fun onRemoveClicked(viewModel: TagViewModel) {
        presenter.onRemoveClicked(viewModel)
    }

}