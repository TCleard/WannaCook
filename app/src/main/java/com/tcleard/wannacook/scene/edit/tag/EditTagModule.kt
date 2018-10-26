package com.tcleard.wannacook.scene.edit.tag

import com.tcleard.wannacook.core.service.ATagService
import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.scene.edit.tag.adapter.SearchTagAdapter
import com.tcleard.wannacook.ui.adapter.tag.TagAdapter
import dagger.Module
import dagger.Provides

@Module
class EditTagModule(
        private val fragment: EditTagFragment
) {

    @Provides
    @SceneScope
    fun providePresenter(tagService: ATagService): EditTagPresenter {
        return EditTagPresenter(tagService)
    }

    @Provides
    @SceneScope
    fun provideSelectedAdapter(): TagAdapter {
        return TagAdapter(fragment)
    }

    @Provides
    @SceneScope
    fun provideSearchAdapter(): SearchTagAdapter {
        return SearchTagAdapter(fragment)
    }

}