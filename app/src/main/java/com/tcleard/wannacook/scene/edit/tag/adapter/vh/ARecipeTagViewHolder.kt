package com.tcleard.wannacook.scene.edit.tag.adapter.vh

import android.view.ViewGroup
import com.tcleard.wannacook.scene.edit.tag.adapter.vm.RecipeTagViewModel
import com.tcleard.wannacook.ui.adapter.AViewHolder

abstract class ARecipeTagViewHolder(
        parent: ViewGroup,
        layoutRes: Int
) : AViewHolder<RecipeTagViewModel>(parent, layoutRes)