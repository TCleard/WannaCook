package com.tcleard.wannacook.core.repo.local.mock

import com.tcleard.wannacook.core.model.Recipe

class MockDB {

    private val recipes = arrayListOf<Recipe>()

    fun getRecipes(): List<Recipe> = recipes
    fun getRecipe(id: String): Recipe? = recipes.firstOrNull { it.id == id }

    fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

    fun addRecipes(vararg recipes: Recipe) {
        this.recipes.addAll(recipes)
    }

    fun deleteRecipes(predicate: (Recipe) -> Boolean) {
        val iterator = recipes.iterator()
        while (iterator.hasNext()) {
            if (predicate.invoke(iterator.next())) {
                iterator.remove()
            }
        }
    }

}