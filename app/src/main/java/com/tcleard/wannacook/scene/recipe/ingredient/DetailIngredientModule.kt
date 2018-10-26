package com.tcleard.wannacook.scene.recipe.ingredient

import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.scene.recipe.ingredient.adapter.DetailIngredientAdapter
import dagger.Module
import dagger.Provides

@Module
class DetailIngredientModule {

    @Provides
    @SceneScope
    fun providePresenter(): DetailIngredientPresenter {
        return DetailIngredientPresenter()
    }

    @Provides
    @SceneScope
    fun provideAdapter(): DetailIngredientAdapter {
        return DetailIngredientAdapter()
    }

}