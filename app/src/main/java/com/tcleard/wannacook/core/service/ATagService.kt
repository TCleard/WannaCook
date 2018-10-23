package com.tcleard.wannacook.core.service

import com.tcleard.wannacook.core.model.PagedList
import com.tcleard.wannacook.core.model.Tag
import io.reactivex.Single

abstract class ATagService : AService<Tag>() {

    abstract fun getRemoteTags(query: String): Single<PagedList<Tag>>

}