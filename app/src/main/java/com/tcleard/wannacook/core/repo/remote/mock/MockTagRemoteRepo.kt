package com.tcleard.wannacook.core.repo.remote.mock

import com.tcleard.wannacook.core.model.PagedList
import com.tcleard.wannacook.core.model.Tag
import com.tcleard.wannacook.core.repo.remote.ITagRemoteRepo
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class MockTagRemoteRepo(
        private val mockApi: MockApi
) : ITagRemoteRepo {

    override fun getTags(query: String): Single<PagedList<Tag>> =
            Single.just(mockApi.getTags(query))
                    .map { PagedList(it) }
                    .delay(700, TimeUnit.MILLISECONDS)

}