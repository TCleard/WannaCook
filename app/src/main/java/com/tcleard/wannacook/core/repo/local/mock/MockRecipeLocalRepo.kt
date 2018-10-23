package com.tcleard.wannacook.core.repo.local.mock

import com.tcleard.wannacook.core.error.NotFoundError
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.repo.local.IRecipeLocalRepo
import io.reactivex.Completable
import io.reactivex.Single

class MockRecipeLocalRepo(
        private val mockDB: MockDB
) : IRecipeLocalRepo {

    override fun getRecipes(): Single<List<Recipe>> =
            Single.just(mockDB.getRecipes())

    override fun getRecipe(id: String): Single<Recipe> =
            Single.create { e ->
                val recipe = mockDB.getRecipe(id)
                if (recipe != null) {
                    e.onSuccess(recipe)
                } else {
                    e.onError(NotFoundError("Recipe $id not found"))
                }
            }

    override fun addRecipes(vararg recipes: Recipe): Completable =
            Completable.create { e ->
                val newIds = recipes.map { it.id }
                mockDB.deleteRecipes { it.id in newIds }
                mockDB.addRecipes(*recipes)
                e.onComplete()
            }

    override fun addRecipe(recipe: Recipe): Completable =
            Completable.create { e ->
                mockDB.deleteRecipes { it.id == recipe.id }
                mockDB.addRecipe(recipe)
                e.onComplete()
            }

}