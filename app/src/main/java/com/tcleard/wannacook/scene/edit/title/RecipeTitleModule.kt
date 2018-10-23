package com.tcleard.wannacook.scene.edit.title

import dagger.Module
import dagger.Provides
import com.tcleard.wannacook.di.SceneScope

@Module
class RecipeTitleModule {

    @Provides
    @SceneScope
    fun providePresenter(): RecipeTitlePresenter {
        return RecipeTitlePresenter()
    }

}