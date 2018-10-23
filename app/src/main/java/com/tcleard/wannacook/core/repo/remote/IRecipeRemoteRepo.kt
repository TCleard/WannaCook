package com.tcleard.wannacook.core.repo.remote

import com.tcleard.wannacook.core.model.PagedList
import com.tcleard.wannacook.core.model.Recipe
import io.reactivex.Single

interface IRecipeRemoteRepo : IRemoteRepo {

    fun getRecipes(): Single<PagedList<Recipe>>
    fun getRecipe(id: String): Single<Recipe>

}