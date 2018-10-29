package com.tcleard.wannacook.scene.ingredient

import android.content.Context
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

    @Provides
    @SceneScope
    fun provideAdapter(context: Context): IngredientUnitAdapter {
        return IngredientUnitAdapter(context)
    }

}