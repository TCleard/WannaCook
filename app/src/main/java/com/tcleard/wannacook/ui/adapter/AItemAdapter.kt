package com.tcleard.wannacook.ui.adapter

import android.support.v7.widget.RecyclerView

abstract class AItemAdapter<I, VH : AViewHolder<*>> : RecyclerView.Adapter<VH>() {

    protected val items = arrayListOf<I>()

    protected var isLoading = false
    protected var isEndless = false

    override fun getItemCount(): Int =
            if (isLoading) {
                2
            } else if (isEndless) {
                items.size + 1
            } else {
                items.size
            }

    open fun getSpanSize(position: Int): Int {
        return getSpanCount()
    }

    open fun getSpanCount(): Int {
        return 1
    }

    open fun setItems(items: List<I>, isLoading: Boolean = false) {
        val previousCount = itemCount
        this.isLoading = isLoading
        this.items.clear()
        this.items.addAll(items)
        when {
            previousCount > itemCount -> {
                notifyItemRangeChanged(0, itemCount)
                notifyItemRangeRemoved(itemCount, previousCount - itemCount)
            }
            previousCount < itemCount -> {
                notifyItemRangeChanged(0, previousCount)
                notifyItemRangeInserted(previousCount, itemCount - previousCount)
            }
            else -> notifyItemRangeChanged(0, itemCount)
        }
    }

    open fun add(item: I, position: Int? = null) {
        add(listOf(item), position)
    }

    open fun add(items: List<I>, position: Int? = null) {
        if (items.isNotEmpty()) {
            if (position != null) {
                this.items.addAll(position, items)
                notifyItemRangeInserted(position, items.size)
            } else {
                val previousCount = itemCount
                this.items.addAll(items)
                notifyItemRangeInserted(previousCount, itemCount - previousCount)
            }
        }
    }

}