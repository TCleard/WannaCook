package com.tcleard.wannacook.core.error

abstract class AError(
        private val reason: String
) : Throwable() {

    override fun printStackTrace() {
        super.printStackTrace()
        println("Application Error - ${javaClass.simpleName} occured : $reason")
    }

}