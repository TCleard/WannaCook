package com.tcleard.wannacook.scene.edit.tag.adapter.vh

import android.view.View
import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.edit.tag.adapter.vm.RecipeTagViewModel
import kotlinx.android.synthetic.main.itemview_tag.view.*

class RecipeTagViewHolder(
        parent: ViewGroup
) : ARecipeTagViewHolder(parent, R.layout.itemview_tag) {

    var onClickListener: ((RecipeTagViewModel) -> Unit)? = null
    var onRemoveClickListener: ((RecipeTagViewModel) -> Unit)? = null

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

    override fun bind(item: RecipeTagViewModel) {
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