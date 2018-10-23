package com.tcleard.wannacook.core.service

class Watching<out T>(
        val action: Action,
        val item: T
) {

    enum class Action {
        ADD,
        UPDATE,
        DELETE
    }

}