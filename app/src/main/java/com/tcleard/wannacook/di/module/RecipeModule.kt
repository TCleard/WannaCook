package com.tcleard.wannacook.di.module

import com.tcleard.wannacook.core.repo.local.IRecipeLocalRepo
import com.tcleard.wannacook.core.repo.local.mock.MockDB
import com.tcleard.wannacook.core.repo.local.mock.MockRecipeLocalRepo
import com.tcleard.wannacook.core.repo.remote.IRecipeRemoteRepo
import com.tcleard.wannacook.core.repo.remote.mock.MockApi
import com.tcleard.wannacook.core.repo.remote.mock.MockRecipeRemoteRepo
import com.tcleard.wannacook.core.service.ARecipeService
import com.tcleard.wannacook.core.service.impl.RecipeService
import com.tcleard.wannacook.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module()
class RecipeModule {

    @Provides
    @ApplicationScope
    fun provideLocalRepo(mockDB: MockDB): IRecipeLocalRepo {
        return MockRecipeLocalRepo(mockDB)
    }

    @Provides
    @ApplicationScope
    fun provideRemoteRepo(mockApi: MockApi): IRecipeRemoteRepo {
        return MockRecipeRemoteRepo(mockApi)
    }

    @Provides
    @ApplicationScope
    fun provideService(localRepo: IRecipeLocalRepo, remoteRepo: IRecipeRemoteRepo): ARecipeService {
        return RecipeService(localRepo, remoteRepo)
    }

}