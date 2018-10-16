package com.tcleard.wannacook.scene.recipe.step

import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.scene.recipe.step.adapter.StepAdapter
import dagger.Module
import dagger.Provides

@Module
class StepModule {

    @Provides
    @SceneScope
    fun providePresenter(): StepPresenter {
        return StepPresenter()
    }

    @Provides
    @SceneScope
    fun provideAdapter(): StepAdapter {
        return StepAdapter()
    }

}