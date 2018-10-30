package com.tcleard.wannacook.scene.edit.tag

import com.tcleard.wannacook.core.service.ATagService
import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.scene.edit.tag.searchAdapter.SearchTagAdapter
import com.tcleard.wannacook.scene.edit.tag.adapter.EditTagAdapter
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
    fun provideSelectedAdapter(): EditTagAdapter {
        return EditTagAdapter(fragment)
    }

    @Provides
    @SceneScope
    fun provideSearchAdapter(): SearchTagAdapter {
        return SearchTagAdapter(fragment)
    }

}