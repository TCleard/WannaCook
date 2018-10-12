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
        var position = 0
        val iterator = items.iterator()
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove()
                notifyItemRemoved(position)
                break
            }
            position++
        }
    }

    open fun remove(items: List<VM>) {
        removeWithIds(items.map { it.getId() })
    }

    open fun removeWithIds(ids: List<String?>) {
        var position = 0
        val iterator = items.iterator()
        while (iterator.hasNext()) {
            if (ids.contains(iterator.next().getId())) {
                iterator.remove()
                notifyItemRemoved(position)
            } else {
                position++
            }
        }
    }

}