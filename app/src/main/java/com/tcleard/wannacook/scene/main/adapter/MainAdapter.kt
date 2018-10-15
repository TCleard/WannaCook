package com.tcleard.wannacook.scene.main.adapter

import android.view.ViewGroup
import com.tcleard.wannacook.core.manager.IImageManager
import com.tcleard.wannacook.scene.main.adapter.vh.RecipeViewHolder
import com.tcleard.wannacook.scene.main.adapter.vm.RecipeViewModel
import com.tcleard.wannacook.ui.adapter.AViewModelAdapter

class MainAdapter(
        private val imageManager: IImageManager
) : AViewModelAdapter<RecipeViewModel, RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder =
            RecipeViewHolder(parent, imageManager)

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(items[position])
    }

}