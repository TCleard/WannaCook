package com.tcleard.wannacook.scene.edit.title

import dagger.Module
import dagger.Provides
import com.tcleard.wannacook.di.SceneScope

@Module
class EditTitleModule {

    @Provides
    @SceneScope
    fun providePresenter(): EditTitlePresenter {
        return EditTitlePresenter()
    }

}