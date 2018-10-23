package com.tcleard.wannacook.scene.edit.tag

import com.tcleard.wannacook.core.service.ATagService
import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.ui.adapter.tag.TagAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class RecipeTagModule(
        private val onItemClickListener: TagAdapter.OnItemClickListener
) {

    @Provides
    @SceneScope
    fun providePresenter(tagService: ATagService): RecipeTagPresenter {
        return RecipeTagPresenter(tagService)
    }

    @Provides
    @SceneScope
    @Named("selected")
    fun provideSelectedAdapter(): TagAdapter {
        return TagAdapter(onItemClickListener)
    }

    @Provides
    @SceneScope
    @Named("search")
    fun provideSearchAdapter(): TagAdapter {
        return TagAdapter(onItemClickListener)
    }

}