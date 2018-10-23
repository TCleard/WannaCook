package com.tcleard.wannacook.core.service

import com.tcleard.wannacook.core.model.PagedList
import com.tcleard.wannacook.core.model.Recipe
import io.reactivex.Single

abstract class ARecipeService : AService<Recipe>() {

    abstract fun getLocalRecipes(): Single<List<Recipe>>

    abstract fun getRemoteRecipes(): Single<PagedList<Recipe>>

}