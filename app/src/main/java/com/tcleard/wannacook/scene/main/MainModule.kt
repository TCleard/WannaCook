package com.tcleard.wannacook.scene.main

import com.tcleard.wannacook.core.manager.IImageManager
import com.tcleard.wannacook.core.manager.ITimeManager
import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.scene.main.adapter.MainAdapter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @SceneScope
    fun providePresenter(timeManager: ITimeManager): MainPresenter {
        return MainPresenter(timeManager)
    }

    @Provides
    @SceneScope
    fun provideAdapter(imageManager: IImageManager): MainAdapter {
        return MainAdapter(imageManager)
    }

}