package com.tcleard.wannacook.scene.recipe

import android.support.v7.app.AppCompatActivity
import com.tcleard.wannacook.di.SceneScope
import dagger.Module
import dagger.Provides

@Module
class RecipeModule(
        private val activity: AppCompatActivity
) {

    @Provides
    @SceneScope
    fun providePresenter(): RecipePresenter {
        return RecipePresenter()
    }

    @Provides
    @SceneScope
    fun provideAdapter(): RecipeFragmentAdapter {
        return RecipeFragmentAdapter(activity.supportFragmentManager)
    }

}