package com.tcleard.wannacook.core.model

import java.io.Serializable

abstract class AModel : Serializable {

    open var id: String? = null

}