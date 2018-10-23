package com.tcleard.wannacook.scene.edit.tag.adapter.vh

import android.view.ViewGroup
import com.tcleard.wannacook.R
import kotlinx.android.synthetic.main.itemview_tag_add.view.*

class AddRecipeTagViewHolder(
        parent: ViewGroup
) : ARecipeTagViewHolder(parent, R.layout.itemview_tag_add) {

    var onClickListener: (() -> Unit)? = null

    init {
        itemView.tagAddBackground.setOnClickListener {
            onClickListener?.invoke()
        }
    }

}