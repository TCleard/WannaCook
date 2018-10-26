package com.tcleard.wannacook.ui.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.tcleard.wannacook.R

class ColorSeparator private constructor(
        context: Context,
        color: Int,
        private val orientation: Orientation,
        private val start: Int,
        private val end: Int
) : RecyclerView.ItemDecoration() {

    companion object {

        fun builder(context: Context): Builder = Builder(context)

    }

    class Builder internal constructor(private val context: Context) {

        private var orientation: Orientation = Orientation.HORIZONTAL
        private var color: Int = context.resources.getColor(R.color.light)
        private var start: Int = 0
        private var end: Int = 0

        fun setColorRes(@ColorRes colorRes: Int): Builder =
                setColor(context.resources.getColor(colorRes))

        fun setColor(color: Int): Builder {
            this.color = color
            return this
        }

        fun setStartPaddingRes(@DimenRes dimenRes: Int): Builder =
                setStartPadding(context.resources.getDimensionPixelSize(dimenRes))

        fun setStartPadding(dimen: Int): Builder {
            start = dimen
            return this
        }

        fun setEndPaddingRes(@DimenRes dimenRes: Int): Builder =
                setEndPadding(context.resources.getDimensionPixelSize(dimenRes))

        fun setEndPadding(dimen: Int): Builder {
            end = dimen
            return this
        }

        fun setPaddingRes(@DimenRes dimen: Int): Builder =
                setPadding(context.resources.getDimensionPixelSize(dimen))

        fun setPadding(dimen: Int): Builder {
            setStartPadding(dimen)
            setEndPadding(dimen)
            return this
        }

        fun setOrientation(orientation: Orientation): Builder {
            this.orientation = orientation
            return this
        }

        fun build(): ColorSeparator = ColorSeparator(context, color, orientation, start, end)

    }

    private val divider: Drawable

    init {
        divider = context.resources.getDrawable(when (orientation) {
            Orientation.HORIZONTAL -> R.drawable.divider_horizontal
            Orientation.VERTICAL -> R.drawable.divider_vertical
        }).mutate()
        divider.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        when (orientation) {
            Orientation.HORIZONTAL -> outRect.top = divider.intrinsicHeight
            Orientation.VERTICAL -> outRect.left = divider.intrinsicWidth
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        var left: Int = 0
        var right: Int = 0
        var top: Int = 0
        var bottom: Int = 0

        when (orientation) {
            Orientation.HORIZONTAL -> {
                left = parent.paddingLeft + start
                right = parent.width - left - end
            }
            Orientation.VERTICAL -> {
                top = parent.paddingTop + start
                bottom = parent.height - top - end
            }
        }

        val childCount = parent.childCount

        (0 until (childCount - 1)).forEach { i ->

            val child = parent.getChildAt(i)

            val layoutParams = child.layoutParams as? RecyclerView.LayoutParams

            when (orientation) {
                Orientation.HORIZONTAL -> {
                    top = child.bottom + (layoutParams?.bottomMargin ?: 0)
                    bottom = top + divider.intrinsicHeight
                }
                Orientation.VERTICAL -> {
                    left = child.right + (layoutParams?.rightMargin ?: 0)
                    right = left + divider.intrinsicWidth
                }
            }

            divider.setBounds(left, top, right, bottom)

            c.let { divider.draw(it) }

        }
    }

    enum class Orientation {
        HORIZONTAL,
        VERTICAL
    }

}