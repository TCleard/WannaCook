package com.tcleard.wannacook.scene.edit.tag

import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.scene.edit.tag.adapter.RecipeTagAdapter
import dagger.Module
import dagger.Provides

@Module
class RecipeTagModule(
        private val onItemClickListener: RecipeTagAdapter.OnItemClickListener
) {

    @Provides
    @SceneScope
    fun providePresenter(): RecipeTagPresenter {
        return RecipeTagPresenter()
    }

    @Provides
    @SceneScope
    fun provideAdapter(): RecipeTagAdapter {
        return RecipeTagAdapter(onItemClickListener)
    }

}