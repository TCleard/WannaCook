package com.tcleard.wannacook.scene.edit.tag.searchAdapter.vh

import android.view.ViewGroup
import com.tcleard.wannacook.scene.edit.tag.searchAdapter.vm.SearchTagViewModel
import com.tcleard.wannacook.ui.adapter.AViewHolder

abstract class ASearchTagViewHolder(
        parent: ViewGroup,
        layoutRes: Int
) : AViewHolder<SearchTagViewModel>(parent, layoutRes)