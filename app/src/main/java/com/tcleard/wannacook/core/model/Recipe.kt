package com.tcleard.wannacook.core.model

class Recipe : AModel() {

    var type: Type = Type()

    var imageUrl: String = ""

    var name: String = ""
    var description: String = ""

    var preparationTime: Long? = null
    var cookingTime: Long? = null

    var rating: Double? = null

    var count: Int = 1

    var ingredients = arrayListOf<Ingredient>()
    var steps = arrayListOf<Step>()

    class Type : AModel() {

        var name: String = ""
        var color: Int? = null

    }

}