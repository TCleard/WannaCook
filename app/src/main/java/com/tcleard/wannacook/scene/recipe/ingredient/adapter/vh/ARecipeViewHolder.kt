package com.tcleard.wannacook.scene.recipe.ingredient.adapter.vh

import android.view.ViewGroup
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.vm.IRecipeViewModel
import com.tcleard.wannacook.ui.adapter.AViewHolder

abstract class ARecipeViewHolder<VM : IRecipeViewModel>(
        parent: ViewGroup,
        layoutRes: Int
) : AViewHolder<VM>(parent, layoutRes)