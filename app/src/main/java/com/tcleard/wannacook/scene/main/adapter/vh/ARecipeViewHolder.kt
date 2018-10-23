package com.tcleard.wannacook.scene.main.adapter.vh

import android.view.ViewGroup
import com.tcleard.wannacook.scene.main.adapter.vm.RecipeViewModel
import com.tcleard.wannacook.ui.adapter.AViewHolder

abstract class ARecipeViewHolder(
        parent: ViewGroup,
        layoutRes: Int
) : AViewHolder<RecipeViewModel>(parent, layoutRes)