package com.tcleard.wannacook.ui.adapter

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class AViewHolder<I>(parent: ViewGroup, layoutRes: Int) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {

    protected val resources: Resources
        get() = itemView.resources

    protected var item: I? = null

    open fun bind(item: I) {
        this.item = item
    }

}