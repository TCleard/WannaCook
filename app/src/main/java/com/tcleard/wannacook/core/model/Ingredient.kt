package com.tcleard.wannacook.core.model

class Ingredient : AModel() {

    var name: String = ""

    var quantity: Double = 0.0

    var unit: Unit = Unit.NONE

    enum class Unit {
        KG,
        G,
        MG,
        L,
        CL,
        SP,
        NONE
    }

}