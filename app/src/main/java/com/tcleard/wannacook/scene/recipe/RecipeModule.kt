package com.tcleard.wannacook.scene.recipe

import dagger.Module
import dagger.Provides
import com.tcleard.wannacook.di.SceneScope

@Module
class RecipeModule {

    @Provides
    @SceneScope
    fun providePresenter(): RecipePresenter {
        return RecipePresenter()
    }

}