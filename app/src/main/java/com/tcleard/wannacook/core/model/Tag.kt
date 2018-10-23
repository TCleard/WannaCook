package com.tcleard.wannacook.core.model

class Tag : AModel() {

    var name: String = ""

    companion object {

        fun builder(): Builder = Builder()

        class Builder {

            private var id = ""
            private var name = ""

            fun id(id: Int): Builder {
                this.id = "$id"
                return this
            }

            fun id(id: String): Builder {
                this.id = id
                return this
            }

            fun name(name: String): Builder {
                this.name = name
                return this
            }

            fun build(): Tag {
                val tag = Tag()
                tag.id = id
                tag.name = name
                return tag
            }

        }

    }

}