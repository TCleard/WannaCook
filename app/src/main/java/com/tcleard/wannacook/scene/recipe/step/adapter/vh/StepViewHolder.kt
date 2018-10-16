package com.tcleard.wannacook.scene.recipe.step.adapter.vh

import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.recipe.step.adapter.vm.StepViewModel
import com.tcleard.wannacook.ui.adapter.AViewHolder
import kotlinx.android.synthetic.main.itemview_step.view.*

class StepViewHolder(
        parent: ViewGroup
) : AViewHolder<StepViewModel>(parent, R.layout.itemview_step) {

    override fun bind(item: StepViewModel) {
        super.bind(item)

        itemView.stepNumber.text = resources.getString(R.string.recipeStepNumber, item.getPosition())
        itemView.stepText.text = item.getStep()

    }

}