package com.tcleard.wannacook.ui.adapter.tag.vh

import android.view.View
import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.ui.adapter.AViewHolder
import com.tcleard.wannacook.ui.adapter.tag.vm.TagViewModel
import kotlinx.android.synthetic.main.itemview_tag.view.*

class TagViewHolder(
        parent: ViewGroup
) : AViewHolder<TagViewModel>(parent, R.layout.itemview_tag) {

    var onClickListener: ((TagViewModel) -> Unit)? = null
    var onRemoveClickListener: ((TagViewModel) -> Unit)? = null

    init {
        itemView.tagBackground.setOnClickListener {
            item?.let { viewModel ->
                onClickListener?.invoke(viewModel)
            }
        }
        itemView.tagRemove.setOnClickListener {
            item?.let { viewModel ->
                onRemoveClickListener?.invoke(viewModel)
            }
        }
    }

    override fun bind(item: TagViewModel) {
        super.bind(item)
        itemView.tagName.text = item.getName()

        if (item.isDeletable()) {
            itemView.tagRemove.visibility = View.VISIBLE
            itemView.tagBackground.isClickable = false
        } else {
            itemView.tagRemove.visibility = View.GONE
            itemView.tagBackground.isClickable = true
        }
    }

}