package com.tcleard.wannacook.scene.edit

import dagger.Module
import dagger.Provides
import com.tcleard.wannacook.di.SceneScope

@Module
class EditRecipeModule {

    @Provides
    @SceneScope
    fun providePresenter(): EditRecipePresenter {
        return EditRecipePresenter()
    }

}