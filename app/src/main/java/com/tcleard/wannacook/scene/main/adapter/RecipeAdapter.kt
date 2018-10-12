package com.tcleard.wannacook.scene.main.adapter

import android.view.ViewGroup
import com.tcleard.wannacook.core.manager.IImageManager
import com.tcleard.wannacook.scene.main.adapter.vh.RecipeViewHolder
import com.tcleard.wannacook.scene.main.adapter.vm.RecipeViewModel
import com.tcleard.wannacook.ui.adapter.AViewModelAdapter

class RecipeAdapter(
        private val imageManager: IImageManager,
        private val listener: OnClickListener
) : AViewModelAdapter<RecipeViewModel, RecipeViewHolder>(), RecipeViewHolder.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder =
            RecipeViewHolder(parent, imageManager, this)

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onRecipeClicked(viewModel: RecipeViewModel) {
        listener.onRecipeClicked(viewModel)
    }

    override fun onFavoriteClicked(viewModel: RecipeViewModel) {
    }

    interface OnClickListener {

        fun onRecipeClicked(viewModel: RecipeViewModel)

    }

}