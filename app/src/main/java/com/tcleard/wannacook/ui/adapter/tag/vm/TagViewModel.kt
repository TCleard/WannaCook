package com.tcleard.wannacook.ui.adapter.tag.vm

import com.tcleard.wannacook.core.model.Tag
import com.tcleard.wannacook.ui.adapter.AViewModel

class TagViewModel(
        tag: Tag,
        private val isDeletable : Boolean
) : AViewModel<Tag>(tag) {

    fun getName(): String = model.name

    fun isDeletable() : Boolean = isDeletable

}