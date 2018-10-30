package com.tcleard.wannacook.scene.edit.title

import com.tcleard.wannacook.core.manager.IKeyboardManager
import com.tcleard.wannacook.di.SceneScope
import dagger.Module
import dagger.Provides

@Module
class EditTitleModule {

    @Provides
    @SceneScope
    fun providePresenter(keyboardManager: IKeyboardManager): EditTitlePresenter {
        return EditTitlePresenter(keyboardManager)
    }

}