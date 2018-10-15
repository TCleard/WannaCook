package com.tcleard.wannacook.core.model

class Step : AModel() {

    var text: String = ""

    companion object {

        fun builder(): Builder = Builder()

        class Builder {

            private var text = ""

            fun text(text: String): Builder {
                this.text = text
                return this
            }

            fun build(): Step {
                val step = Step()
                step.text = text
                return step
            }

        }

    }

}