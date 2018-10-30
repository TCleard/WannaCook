package com.tcleard.wannacook.scene.edit.tag.searchAdapter.vm

import com.tcleard.wannacook.core.model.Tag
import com.tcleard.wannacook.ui.adapter.AViewModel

class SearchTagViewModel(
        tag: Tag
) : AViewModel<Tag>(tag) {

    fun getName(): String = model.name

}