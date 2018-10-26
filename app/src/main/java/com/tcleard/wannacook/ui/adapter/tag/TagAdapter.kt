package com.tcleard.wannacook.ui.adapter.tag

import android.view.ViewGroup
import com.tcleard.wannacook.ui.adapter.AViewModelAdapter
import com.tcleard.wannacook.ui.adapter.tag.vh.TagViewHolder
import com.tcleard.wannacook.ui.adapter.tag.vm.TagViewModel

class TagAdapter(
        private val onItemClickListener: OnItemClickListener
) : AViewModelAdapter<TagViewModel, TagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val holder = TagViewHolder(parent)
        holder.onRemoveClickListener = {
            onItemClickListener.onRemoveClicked(it)
        }
        return holder
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface OnItemClickListener {

        fun onRemoveClicked(viewModel: TagViewModel)

    }

}