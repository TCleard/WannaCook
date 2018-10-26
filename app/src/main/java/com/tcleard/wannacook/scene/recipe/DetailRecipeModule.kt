package com.tcleard.wannacook.scene.recipe

import android.support.v7.app.AppCompatActivity
import com.tcleard.wannacook.core.manager.ITimeManager
import com.tcleard.wannacook.di.SceneScope
import dagger.Module
import dagger.Provides

@Module
class DetailRecipeModule(
        private val activity: AppCompatActivity
) {

    @Provides
    @SceneScope
    fun providePresenter(timeManager: ITimeManager): DetailRecipePresenter {
        return DetailRecipePresenter(timeManager)
    }

    @Provides
    @SceneScope
    fun provideAdapter(): DetailRecipeFragmentAdapter {
        return DetailRecipeFragmentAdapter(activity.supportFragmentManager)
    }

}