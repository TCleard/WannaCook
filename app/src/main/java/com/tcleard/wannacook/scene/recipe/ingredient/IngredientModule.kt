package com.tcleard.wannacook.scene.recipe.ingredient

import dagger.Module
import dagger.Provides
import com.tcleard.wannacook.di.SceneScope

@Module
class IngredientModule {

    @Provides
    @SceneScope
    fun providePresenter(): IngredientPresenter {
        return IngredientPresenter()
    }

}