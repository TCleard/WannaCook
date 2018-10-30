package com.tcleard.wannacook.scene.edit.tag.adapter.vh

import android.view.View
import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.ui.adapter.AViewHolder
import com.tcleard.wannacook.scene.edit.tag.adapter.vm.EditTagViewModel
import kotlinx.android.synthetic.main.itemview_edit_tag.view.*

class EditTagViewHolder(
        parent: ViewGroup
) : AViewHolder<EditTagViewModel>(parent, R.layout.itemview_edit_tag) {

    var onRemoveClickListener: ((EditTagViewModel) -> Unit)? = null

    init {
        itemView.editTagRemove.setOnClickListener {
            item?.let { viewModel ->
                onRemoveClickListener?.invoke(viewModel)
            }
        }
    }

    override fun bind(item: EditTagViewModel) {
        super.bind(item)
        itemView.editTagName.text = item.getName()

        if (item.isDeletable()) {
            itemView.editTagRemove.visibility = View.VISIBLE
        } else {
            itemView.editTagRemove.visibility = View.GONE
        }

    }

}