package com.tcleard.wannacook.scene.edit.ingredient.adapter

import android.view.ViewGroup
import com.tcleard.wannacook.scene.edit.ingredient.adapter.vh.EditIngredientViewHolder
import com.tcleard.wannacook.scene.edit.ingredient.adapter.vm.EditIngredientViewModel
import com.tcleard.wannacook.ui.adapter.AViewModelAdapter

class EditIngredientAdapter(
        private val onItemClickListener: OnItemClickListener
) : AViewModelAdapter<EditIngredientViewModel, EditIngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditIngredientViewHolder {
        val holder = EditIngredientViewHolder(parent)
        holder.onEditClickListener = {
            onItemClickListener.onEditClicked(it)
        }
        holder.onDeleteClickListener = {
            onItemClickListener.onDeleteClicked(it)
        }
        return holder
    }

    override fun onBindViewHolder(holder: EditIngredientViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface OnItemClickListener {

        fun onEditClicked(viewModel: EditIngredientViewModel)
        fun onDeleteClicked(viewModel: EditIngredientViewModel)

    }

}