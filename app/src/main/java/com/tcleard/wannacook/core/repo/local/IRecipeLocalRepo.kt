package com.tcleard.wannacook.core.repo.local

import com.tcleard.wannacook.core.model.Recipe
import io.reactivex.Completable
import io.reactivex.Single

interface IRecipeLocalRepo : ILocalRepo {

    fun getRecipes(): Single<List<Recipe>>
    fun getRecipe(id: String): Single<Recipe>

    fun addRecipes(vararg recipes: Recipe): Completable
    fun addRecipe(recipe: Recipe): Completable

}