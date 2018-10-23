package com.tcleard.wannacook.scene.main.adapter

import android.view.ViewGroup
import com.tcleard.wannacook.core.manager.IImageManager
import com.tcleard.wannacook.scene.main.adapter.vh.ARecipeViewHolder
import com.tcleard.wannacook.scene.main.adapter.vh.LoadingRecipeViewHolder
import com.tcleard.wannacook.scene.main.adapter.vh.RecipeViewHolder
import com.tcleard.wannacook.scene.main.adapter.vm.RecipeViewModel
import com.tcleard.wannacook.ui.adapter.AViewModelAdapter

class MainAdapter(
        private val imageManager: IImageManager
) : AViewModelAdapter<RecipeViewModel, ARecipeViewHolder>() {

    companion object {

        const val TYPE_RECIPE = 1
        const val TYPE_LOADING = 2

    }

    init {
        isLoading = true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ARecipeViewHolder =
            when (viewType) {
                TYPE_RECIPE -> RecipeViewHolder(parent, imageManager)
                else -> LoadingRecipeViewHolder(parent)
            }

    override fun onBindViewHolder(holder: ARecipeViewHolder, position: Int) {
        if (holder is RecipeViewHolder) {
            holder.bind(items[position])
        }
    }

    override fun getItemViewType(position: Int): Int =
            if (isLoading) {
                TYPE_LOADING
            } else {
                TYPE_RECIPE
            }

}