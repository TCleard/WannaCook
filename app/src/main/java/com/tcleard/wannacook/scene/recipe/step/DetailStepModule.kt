package com.tcleard.wannacook.scene.recipe.step

import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.scene.recipe.step.adapter.StepAdapter
import dagger.Module
import dagger.Provides

@Module
class DetailStepModule {

    @Provides
    @SceneScope
    fun providePresenter(): DetailStepPresenter {
        return DetailStepPresenter()
    }

    @Provides
    @SceneScope
    fun provideAdapter(): StepAdapter {
        return StepAdapter()
    }

}