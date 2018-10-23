package com.tcleard.wannacook.scene.edit.tag.adapter

import android.view.ViewGroup
import com.tcleard.wannacook.scene.edit.tag.adapter.vh.ARecipeTagViewHolder
import com.tcleard.wannacook.scene.edit.tag.adapter.vh.AddRecipeTagViewHolder
import com.tcleard.wannacook.scene.edit.tag.adapter.vh.RecipeTagViewHolder
import com.tcleard.wannacook.scene.edit.tag.adapter.vm.RecipeTagViewModel
import com.tcleard.wannacook.ui.adapter.AViewModelAdapter

class RecipeTagAdapter(
        private val onItemClickListener: OnItemClickListener
) : AViewModelAdapter<RecipeTagViewModel, ARecipeTagViewHolder>() {

    companion object {

        const val TYPE_TAG = 1
        const val TYPE_ADD = 2

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ARecipeTagViewHolder =
            when (viewType) {
                TYPE_TAG -> {
                    val holder = RecipeTagViewHolder(parent)
                    holder.onRemoveClickListener = {
                        onItemClickListener.onRemoveClicked(it)
                    }
                    holder
                }
                else -> {
                    val holder = AddRecipeTagViewHolder(parent)
                    holder.onClickListener = {
                        onItemClickListener.onAddClicked()
                    }
                    holder
                }
            }

    override fun onBindViewHolder(holder: ARecipeTagViewHolder, position: Int) {
        if (holder is RecipeTagViewHolder) {
            holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int = items.size + 1

    override fun getItemViewType(position: Int): Int =
            if (position == itemCount - 1) {
                TYPE_ADD
            } else {
                TYPE_TAG
            }

    interface OnItemClickListener {

        fun onAddClicked()

        fun onRemoveClicked(viewModel: RecipeTagViewModel)

    }

}