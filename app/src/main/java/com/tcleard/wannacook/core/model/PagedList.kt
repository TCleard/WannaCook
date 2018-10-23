package com.tcleard.wannacook.core.model

class PagedList<T>(
        val items: List<T>,
        val next: String = ""
) {

    val size: Int
        get() = items.size

    fun isEmpty(): Boolean = size == 0

    fun isNotEmpty(): Boolean = size > 0

    fun hasNext(): Boolean = next.isNotBlank()

}