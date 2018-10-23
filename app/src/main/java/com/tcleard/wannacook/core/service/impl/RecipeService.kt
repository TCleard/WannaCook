package com.tcleard.wannacook.core.service.impl

import com.tcleard.wannacook.core.extension.rx.sub
import com.tcleard.wannacook.core.model.PagedList
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.repo.local.IRecipeLocalRepo
import com.tcleard.wannacook.core.repo.remote.IRecipeRemoteRepo
import com.tcleard.wannacook.core.service.ARecipeService
import io.reactivex.Single

class RecipeService(
        private val localRepo: IRecipeLocalRepo,
        private val remoteRepo: IRecipeRemoteRepo
) : ARecipeService() {

    override fun getLocalRecipes(): Single<List<Recipe>> =
            localRepo.getRecipes()

    override fun getRemoteRecipes(): Single<PagedList<Recipe>> =
            remoteRepo.getRecipes()
                    .doOnSuccess {
                        localRepo.addRecipes(*it.items.toTypedArray())
                                .sub()
                    }

}