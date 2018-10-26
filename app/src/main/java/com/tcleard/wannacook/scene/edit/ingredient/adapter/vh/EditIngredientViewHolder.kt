package com.tcleard.wannacook.scene.edit.ingredient.adapter.vh

import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.edit.ingredient.adapter.vm.EditIngredientViewModel
import com.tcleard.wannacook.ui.adapter.AViewHolder
import kotlinx.android.synthetic.main.itemview_edit_ingredient.view.*

class EditIngredientViewHolder(
        parent: ViewGroup
) : AViewHolder<EditIngredientViewModel>(parent, R.layout.itemview_edit_ingredient) {

    var onEditClickListener: ((EditIngredientViewModel) -> Unit)? = null
    var onDeleteClickListener: ((EditIngredientViewModel) -> Unit)? = null

    init {

        itemView.editIngredientEdit.setOnClickListener {
            item?.let { viewModel -> onEditClickListener?.invoke(viewModel) }
        }
        itemView.editIngredientDelete.setOnClickListener {
            item?.let { viewModel -> onDeleteClickListener?.invoke(viewModel) }
        }

    }

    override fun bind(item: EditIngredientViewModel) {
        super.bind(item)

        val components = arrayListOf<String>()

        item.getQuantity()?.let { components.add(it) }
        item.getUnit()?.let { components.add(resources.getString(it).decapitalize()) }

        if (item.hasConnector()) {
            components.add(resources.getString(R.string.connector))
        }

        if (components.isEmpty()) {
            components.add(item.getName())
        } else {
            components.add(item.getName().decapitalize())
        }

        itemView.editIngredientText.text = components.joinToString(" ")

    }

}