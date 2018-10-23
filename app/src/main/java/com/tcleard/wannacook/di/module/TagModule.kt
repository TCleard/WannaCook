package com.tcleard.wannacook.di.module

import com.tcleard.wannacook.core.repo.remote.ITagRemoteRepo
import com.tcleard.wannacook.core.repo.remote.mock.MockApi
import com.tcleard.wannacook.core.repo.remote.mock.MockTagRemoteRepo
import com.tcleard.wannacook.core.service.ATagService
import com.tcleard.wannacook.core.service.impl.TagService
import com.tcleard.wannacook.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class TagModule {

    @Provides
    @ApplicationScope
    fun provideRemoteRepo(mockApi: MockApi): ITagRemoteRepo {
        return MockTagRemoteRepo(mockApi)
    }

    @Provides
    @ApplicationScope
    fun provideService(remoteRepo: ITagRemoteRepo): ATagService {
        return TagService(remoteRepo)
    }

}