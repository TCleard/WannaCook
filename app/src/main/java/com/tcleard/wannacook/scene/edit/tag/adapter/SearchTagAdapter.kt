package com.tcleard.wannacook.scene.edit.tag.adapter

import android.view.ViewGroup
import com.tcleard.wannacook.scene.edit.tag.adapter.vh.ASearchTagViewHolder
import com.tcleard.wannacook.scene.edit.tag.adapter.vh.LoadingSearchTagViewHolder
import com.tcleard.wannacook.scene.edit.tag.adapter.vh.SearchTagViewHolder
import com.tcleard.wannacook.scene.edit.tag.adapter.vm.SearchTagViewModel
import com.tcleard.wannacook.ui.adapter.AViewModelAdapter

class SearchTagAdapter(
        private val onTagClickListener: OnTagClickListener
) : AViewModelAdapter<SearchTagViewModel, ASearchTagViewHolder>() {

    companion object {

        const val TYPE_TAG = 1
        const val TYPE_LOADING = 2

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ASearchTagViewHolder =
            when (viewType) {
                TYPE_TAG -> {
                    val holder = SearchTagViewHolder(parent)
                    holder.onClickListener = {
                        onTagClickListener.onTagClicked(it)
                    }
                    holder
                }
                else -> LoadingSearchTagViewHolder(parent)
            }

    override fun onBindViewHolder(holder: ASearchTagViewHolder, position: Int) {
        if (holder is SearchTagViewHolder) {
            holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int =
            if (isLoading) {
                items.size + 2
            } else {
                items.size
            }

    override fun getItemViewType(position: Int): Int =
            if (isLoading && position > items.size - 1) {
                TYPE_LOADING
            } else {
                TYPE_TAG
            }

    fun notifyLoading() {
        if (!isLoading) {
            val previousCount = itemCount
            isLoading = true
            when {
                previousCount > itemCount -> {
                    notifyItemRangeChanged(0, itemCount)
                    notifyItemRangeRemoved(itemCount, previousCount - itemCount)
                }
                previousCount < itemCount -> {
                    notifyItemRangeChanged(0, previousCount)
                    notifyItemRangeInserted(previousCount, itemCount - previousCount)
                }
                else -> notifyItemRangeChanged(0, itemCount)
            }
        }
    }

    interface OnTagClickListener {

        fun onTagClicked(viewModel: SearchTagViewModel)

    }

}