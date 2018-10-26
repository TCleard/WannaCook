package com.tcleard.wannacook.core.model

import com.tcleard.wannacook.R

class Ingredient : AModel() {

    override var id: String? = ""
        get() = name

    var name: String = ""

    var quantity: Double = 0.0

    var unit: Unit = Unit.NONE

    enum class Unit(val res: Int? = null) {
        KG(R.string.kg),
        G(R.string.g),
        MG(R.string.mg),
        L(R.string.l),
        CL(R.string.cl),
        SP(R.string.sp),
        NONE()
    }

    companion object {

        fun builder(): Builder = Builder()

        class Builder {

            private var name = ""
            private var quantity = 0.0
            private var unit = Unit.NONE

            fun name(name: String): Builder {
                this.name = name
                return this
            }

            fun quantity(quantity: Double): Builder {
                this.quantity = quantity
                return this
            }

            fun unit(unit: Unit): Builder {
                this.unit = unit
                return this
            }

            fun build(): Ingredient {
                val ingredient = Ingredient()
                ingredient.name = name
                ingredient.quantity = quantity
                ingredient.unit = unit
                return ingredient
            }

        }

    }

}