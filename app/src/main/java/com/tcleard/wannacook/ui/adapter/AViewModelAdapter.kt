package com.tcleard.wannacook.ui.adapter

abstract class AViewModelAdapter<VM : AViewModel<*>, VH : AViewHolder<*>> :
        AItemAdapter<VM, VH>() {

    open fun contains(item: VM): Boolean =
            items.any { it.getId() == item.getId() }

    open fun update(item: VM) {
        for (i in 0 until items.size) {
            if (items[i].getId() == item.getId()) {
                items[i] = item
                notifyItemChanged(i)
                break
            }
        }
    }

    open fun update(items: List<VM>) {
        items.forEach { update(it) }
    }

    open fun remove(item: VM) {
        removeWithId(item.getId())
    }

    open fun removeWithId(id: String?) {
        remove { it.getId() == id }
    }

    open fun remove(items: List<VM>) {
        removeWithIds(items.map { it.getId() })
    }

    open fun removeWithIds(ids: List<String?>) {
        remove { it.getId() in ids }
    }

}