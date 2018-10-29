package com.tcleard.wannacook.ui.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.tcleard.wannacook.R

class ColorSeparator private constructor(
        context: Context,
        private val builder: Builder
) : RecyclerView.ItemDecoration() {

    companion object {

        fun builder(context: Context): Builder = Builder(context)

    }

    class Builder internal constructor(private val context: Context) {

        var orientation: Orientation = Orientation.HORIZONTAL
        var color: Int = context.resources.getColor(R.color.light)
        var backgroundColor: Int? = null
        var start: Int = 0
        var end: Int = 0

        fun setColorRes(@ColorRes colorRes: Int): Builder =
                setColor(context.resources.getColor(colorRes))

        fun setColor(color: Int): Builder {
            this.color = color
            return this
        }

        fun setBackgroundColorRes(@ColorRes backgroundColorRes: Int?): Builder =
                setBackgroundColor(backgroundColorRes?.let { context.resources.getColor(it) })

        fun setBackgroundColor(backgroundColor: Int?): Builder {
            this.backgroundColor = backgroundColor
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

        fun build(): ColorSeparator = ColorSeparator(context, this)

    }

    private val divider: Drawable
    private val background: Drawable?

    init {
        divider = context.resources.getDrawable(when (builder.orientation) {
            Orientation.HORIZONTAL -> R.drawable.divider_horizontal
            Orientation.VERTICAL -> R.drawable.divider_vertical
        }).mutate()
        divider.setColorFilter(builder.color, PorterDuff.Mode.MULTIPLY)
        background = builder.backgroundColor?.let {
            ColorDrawable(it)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        when (builder.orientation) {
            Orientation.HORIZONTAL -> outRect.top = divider.intrinsicHeight
            Orientation.VERTICAL -> outRect.left = divider.intrinsicWidth
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        var backgroundLeft: Int = 0
        var backgroundRight: Int = 0
        var backgroundTop: Int = 0
        var backgroundBottom: Int = 0
        var left: Int = 0
        var right: Int = 0
        var top: Int = 0
        var bottom: Int = 0

        when (builder.orientation) {
            Orientation.HORIZONTAL -> {
                backgroundLeft = parent.paddingLeft
                backgroundRight = parent.width - parent.paddingRight
                left = backgroundLeft + builder.start
                right = backgroundRight - builder.end
            }
            Orientation.VERTICAL -> {
                backgroundTop = parent.paddingTop
                backgroundBottom = parent.height - parent.paddingBottom
                top = backgroundTop + builder.start
                bottom = backgroundBottom - top - builder.end
            }
        }

        val childCount = parent.childCount

        (0 until (childCount - 1)).forEach { i ->

            val child = parent.getChildAt(i)

            val layoutParams = child.layoutParams as? RecyclerView.LayoutParams

            when (builder.orientation) {
                Orientation.HORIZONTAL -> {
                    top = child.bottom + (layoutParams?.bottomMargin ?: 0)
                    bottom = top + divider.intrinsicHeight
                    backgroundTop = top
                    backgroundBottom = bottom
                }
                Orientation.VERTICAL -> {
                    left = child.right + (layoutParams?.rightMargin ?: 0)
                    right = left + divider.intrinsicWidth
                    backgroundLeft = left
                    backgroundRight = right
                }
            }

            background?.let {
                it.setBounds(backgroundLeft, backgroundTop, backgroundRight, backgroundBottom)
                it.draw(c)
            }

            divider.setBounds(left, top, right, bottom)

            divider.draw(c)

        }

    }

    enum class Orientation {
        HORIZONTAL,
        VERTICAL
    }

}