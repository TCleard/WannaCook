package com.tcleard.wannacook.ui.adapter.tag.vh

import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.ui.adapter.AViewHolder
import com.tcleard.wannacook.ui.adapter.tag.vm.TagViewModel
import kotlinx.android.synthetic.main.itemview_tag.view.*

class TagViewHolder(
        parent: ViewGroup
) : AViewHolder<TagViewModel>(parent, R.layout.itemview_tag) {

    override fun bind(item: TagViewModel) {
        super.bind(item)
        itemView.tagName.text = item.getName()
    }

}