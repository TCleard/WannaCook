package com.tcleard.wannacook.core.service.impl

import com.tcleard.wannacook.core.model.PagedList
import com.tcleard.wannacook.core.model.Tag
import com.tcleard.wannacook.core.repo.remote.ITagRemoteRepo
import com.tcleard.wannacook.core.service.ATagService
import io.reactivex.Single

class TagService(
        private val remoteRepo: ITagRemoteRepo
) : ATagService() {

    override fun getRemoteTags(query: String): Single<PagedList<Tag>> =
            remoteRepo.getTags(query)

}