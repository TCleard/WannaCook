package com.tcleard.wannacook.scene.edit.tag.adapter.vh

import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.edit.tag.adapter.vm.SearchTagViewModel
import kotlinx.android.synthetic.main.itemview_tag_search.view.*

class SearchTagViewHolder(
        parent: ViewGroup
) : ASearchTagViewHolder(parent, R.layout.itemview_tag_search) {

    var onClickListener: ((SearchTagViewModel) -> Unit)? = null

    init {
        itemView.setOnClickListener {
            item?.let { viewModel -> onClickListener?.invoke(viewModel) }
        }
    }

    override fun bind(item: SearchTagViewModel) {
        super.bind(item)
        itemView.searchTagName.text = item.getName()
    }

}