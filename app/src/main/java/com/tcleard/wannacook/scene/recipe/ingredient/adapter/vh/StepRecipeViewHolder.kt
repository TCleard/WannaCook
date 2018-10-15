package com.tcleard.wannacook.scene.recipe.ingredient.adapter.vh

import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.StepRecipeViewModel
import kotlinx.android.synthetic.main.itemview_recipe_step.view.*

class StepRecipeViewHolder(
        parent: ViewGroup
) : ARecipeViewHolder<StepRecipeViewModel>(parent, R.layout.itemview_recipe_step) {

    override fun bind(item: StepRecipeViewModel) {
        super.bind(item)

        itemView.stepRecipeNumber.text = resources.getString(R.string.recipeStepNumber, item.getPosition())
        itemView.stepRecipeText.text = item.getStep()

    }

}