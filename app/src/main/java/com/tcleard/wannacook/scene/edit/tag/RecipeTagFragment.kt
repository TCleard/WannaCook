package com.tcleard.wannacook.scene.edit.tag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexboxLayoutManager
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.edit.AEditRecipeFragment
import com.tcleard.wannacook.scene.edit.tag.adapter.RecipeTagAdapter
import com.tcleard.wannacook.scene.edit.tag.adapter.vm.RecipeTagViewModel
import kotlinx.android.synthetic.main.fragment_recipe_tag.*
import javax.inject.Inject

class RecipeTagFragment : AEditRecipeFragment<RecipeTagPresenter>(), RecipeTagPresenter.RecipeTagView, RecipeTagAdapter.OnItemClickListener {

    @Inject
    lateinit var adapter: RecipeTagAdapter

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

        recipeTagList.layoutManager = FlexboxLayoutManager(requireContext())
        recipeTagList.adapter = adapter

    }

    /** RecipeTagView **/

    override fun setState(state: Boolean) {
        statePublisher.onNext(state)
    }

    override fun showTags(viewModels: List<RecipeTagViewModel>) {
        adapter.setItems(viewModels)
    }

    /** Listeners **/

    override fun onAddClicked() {
    }

    override fun onRemoveClicked(viewModel: RecipeTagViewModel) {
    }

}