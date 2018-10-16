package com.tcleard.wannacook.scene.recipe.ingredient

import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.IngredientAdapter
import dagger.Module
import dagger.Provides

@Module
class IngredientModule {

    @Provides
    @SceneScope
    fun providePresenter(): IngredientPresenter {
        return IngredientPresenter()
    }

    @Provides
    @SceneScope
    fun provideAdapter(): IngredientAdapter {
        return IngredientAdapter()
    }

}