package com.tcleard.wannacook.core.repo.remote

import com.tcleard.wannacook.core.model.PagedList
import com.tcleard.wannacook.core.model.Tag
import io.reactivex.Single

interface ITagRemoteRepo : IRemoteRepo {

    fun getTags(query: String): Single<PagedList<Tag>>

}