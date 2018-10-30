package com.tcleard.wannacook.scene.edit.tag.adapter.vm

import com.tcleard.wannacook.core.model.Tag
import com.tcleard.wannacook.ui.adapter.AViewModel

class EditTagViewModel(
        tag: Tag,
        private val isDeletable : Boolean
) : AViewModel<Tag>(tag) {

    fun getName(): String = model.name

    fun isDeletable() : Boolean = isDeletable

}