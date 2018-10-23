package com.tcleard.wannacook.core.repo.remote.mock

import com.tcleard.wannacook.core.error.NotFoundError
import com.tcleard.wannacook.core.model.PagedList
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.repo.remote.IRecipeRemoteRepo
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class MockRecipeRemoteRepo(
        private val mockApi: MockApi
) : IRecipeRemoteRepo {

    override fun getRecipes(): Single<PagedList<Recipe>> =
            Single.just(mockApi.getRecipes())
                    .map { PagedList(it) }
                    .delay(1500, TimeUnit.MILLISECONDS)

    override fun getRecipe(id: String): Single<Recipe> =
            Single.create<Recipe> { e ->
                val recipe = mockApi.getRecipe(id)
                if (recipe != null) {
                    e.onSuccess(recipe)
                } else {
                    e.onError(NotFoundError("Recipe with id $id not found"))
                }
            }.delay(1500, TimeUnit.MILLISECONDS)

}