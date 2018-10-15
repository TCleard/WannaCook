package com.tcleard.wannacook.ui.decoration

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.tcleard.wannacook.R

class ComponentDecoration(
        private val context: Context,
        private vararg val components: Component = emptyArray()
) : RecyclerView.ItemDecoration() {

    enum class Component {
        TOOLBAR,
        FAB
    }

    private val toolbarPadding: Int by lazy {
        context.resources.getDimensionPixelSize(R.dimen.toolbarHeight)
    }

    private val fabPadding: Int by lazy {
        2 * context.resources.getDimensionPixelSize(R.dimen.marginBig) +
                context.resources.getDimensionPixelSize(R.dimen.fabSize)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildLayoutPosition(view)
        if (position == 0 && components.contains(Component.TOOLBAR)) {
            outRect.top = toolbarPadding
        } else if (position == state.itemCount - 1 && components.contains(Component.FAB)) {
            outRect.bottom = fabPadding
        }
    }

}