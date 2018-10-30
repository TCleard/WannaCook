package com.tcleard.wannacook.scene.edit.tag.adapter

import android.view.ViewGroup
import com.tcleard.wannacook.ui.adapter.AViewModelAdapter
import com.tcleard.wannacook.scene.edit.tag.adapter.vh.EditTagViewHolder
import com.tcleard.wannacook.scene.edit.tag.adapter.vm.EditTagViewModel

class EditTagAdapter(
        private val onItemClickListener: OnItemClickListener
) : AViewModelAdapter<EditTagViewModel, EditTagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditTagViewHolder {
        val holder = EditTagViewHolder(parent)
        holder.onRemoveClickListener = {
            onItemClickListener.onRemoveClicked(it)
        }
        return holder
    }

    override fun onBindViewHolder(holder: EditTagViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface OnItemClickListener {

        fun onRemoveClicked(viewModel: EditTagViewModel)

    }

}