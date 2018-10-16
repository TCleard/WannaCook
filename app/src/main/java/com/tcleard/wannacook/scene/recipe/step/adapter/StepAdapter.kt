package com.tcleard.wannacook.scene.recipe.step.adapter

import android.view.ViewGroup
import com.tcleard.wannacook.scene.recipe.step.adapter.vh.StepViewHolder
import com.tcleard.wannacook.scene.recipe.step.adapter.vm.StepViewModel
import com.tcleard.wannacook.ui.adapter.AViewModelAdapter

class StepAdapter : AViewModelAdapter<StepViewModel, StepViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder =
            StepViewHolder(parent)

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.bind(items[position])
    }

}