package com.tcleard.wannacook.core.model

class Recipe : AModel() {

    var imageUrl: String = ""

    var name: String = ""
    var description: String = ""

    var color: Int? = null

    var preparationTime: Long? = null
    var cookingTime: Long? = null

    var rating: Double? = null

    var count: Int = 1

    var ingredients = arrayListOf<Ingredient>()
    var steps = arrayListOf<Step>()

    var tags = arrayListOf<Tag>()


    companion object {

        fun builder(): Builder = Builder()

        class Builder {

            private val recipe = Recipe()

            fun id(id: Int): Builder {
                recipe.id = "$id"
                return this
            }

            fun id(id: String): Builder {
                recipe.id = id
                return this
            }

            fun imageUrl(imageUrl: String): Builder {
                recipe.imageUrl = imageUrl
                return this
            }

            fun name(name: String): Builder {
                recipe.name = name
                return this
            }

            fun description(description: String): Builder {
                recipe.description = description
                return this
            }

            fun color(color: Int): Builder {
                recipe.color = color
                return this
            }

            fun preparationTime(preparationTime: Long): Builder {
                recipe.preparationTime = preparationTime
                return this
            }

            fun cookingTime(cookingTime: Long): Builder {
                recipe.cookingTime = cookingTime
                return this
            }

            fun rating(rating: Double): Builder {
                recipe.rating = rating
                return this
            }

            fun count(count: Int): Builder {
                recipe.count = count
                return this
            }

            fun add(ingredient: Ingredient): Builder {
                recipe.ingredients.add(ingredient)
                return this
            }

            fun add(step: Step): Builder {
                recipe.steps.add(step)
                return this
            }

            fun add(tag: Tag): Builder {
                recipe.tags.add(tag)
                return this
            }

            fun build(): Recipe = recipe

        }

    }

}