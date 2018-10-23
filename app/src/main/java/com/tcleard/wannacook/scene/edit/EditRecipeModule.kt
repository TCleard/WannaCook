package com.tcleard.wannacook.scene.edit

import android.support.v7.app.AppCompatActivity
import com.tcleard.wannacook.di.SceneScope
import dagger.Module
import dagger.Provides

@Module
class EditRecipeModule(
        private val activity: AppCompatActivity
) {

    @Provides
    @SceneScope
    fun providePresenter(): EditRecipePresenter {
        return EditRecipePresenter()
    }

    @Provides
    @SceneScope
    fun provideAdapter(): EditRecipeFragmentAdapter {
        return EditRecipeFragmentAdapter(activity.supportFragmentManager)
    }

}