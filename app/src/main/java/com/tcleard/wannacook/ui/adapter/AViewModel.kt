package com.tcleard.wannacook.ui.adapter

import com.tcleard.wannacook.core.model.AModel

abstract class AViewModel<out M : AModel>(
        protected val model: M
) {

    open fun getId(): String? = model.id

}