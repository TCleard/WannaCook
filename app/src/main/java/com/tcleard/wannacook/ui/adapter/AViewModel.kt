package com.tcleard.wannacook.ui.adapter

import com.tcleard.wannacook.core.model.AModel

abstract class AViewModel<out M : AModel>(
        val model: M
) {

    open fun getId(): String? = model.id

}