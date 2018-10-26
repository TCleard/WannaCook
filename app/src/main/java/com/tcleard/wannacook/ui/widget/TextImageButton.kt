package com.tcleard.wannacook.ui.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.*
import android.support.v4.widget.ImageViewCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import com.tcleard.wannacook.R
import kotlinx.android.synthetic.main.widget_text_image_button.view.*

class TextImageButton : FrameLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs, defStyleAttr)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs, defStyleAttr, defStyleRes)
    }

    private fun init(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int = 0) {

        inflate(context, R.layout.widget_text_image_button, this)

        val a = context.obtainStyledAttributes(attrs, R.styleable.TextImageButton, defStyleAttr, defStyleRes)
        val defaultStyle = context.obtainStyledAttributes(R.style.ButtonStyle, intArrayOf(android.R.attr.textColor, android.R.attr.textSize))

        try {
            setTint(a.getColor(R.styleable.TextImageButton_tib_tint, defaultStyle.getColor(0, resources.getColor(R.color.white))))
            setText(a.getString(R.styleable.TextImageButton_tib_text))
            setTextSize(a.getDimension(R.styleable.TextImageButton_tib_text_size, defaultStyle.getDimension(1, resources.getDimension(R.dimen.textRegular))))
            setTextPadding(a.getDimensionPixelSize(R.styleable.TextImageButton_tib_text_padding, 0))
            setImageDrawable(a.getDrawable(R.styleable.TextImageButton_tib_image))
            setImagePadding(a.getDimensionPixelSize(R.styleable.TextImageButton_tib_image_padding, 0))
            setImagePosition(a.getInt(R.styleable.TextImageButton_tib_image_position, ImagePosition.START.value))
        } finally {
            a.recycle()
            defaultStyle.recycle()
        }

    }


    fun setTint(@ColorInt tint: Int) {
        tibText.setTextColor(tint)
        ImageViewCompat.setImageTintList(tibImageLeft, ColorStateList.valueOf(tint))
        ImageViewCompat.setImageTintList(tibImageRight, ColorStateList.valueOf(tint))
    }

    fun setTintRes(@ColorRes tintRes: Int) {
        setTint(resources.getColor(tintRes))
    }

    fun setText(text: String?) {
        tibText.text = text
    }

    fun setText(@StringRes textRes: Int) {
        setText(resources.getString(textRes))
    }

    fun setTextSize(textSize: Float) {
        tibText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
    }

    fun setTextSizeRes(@DimenRes textSizeRes: Int) {
        setTextSize(resources.getDimension(textSizeRes))
    }

    fun setTextPadding(padding: Int) {
        tibText.setPadding(padding, padding, padding, padding)
    }

    fun setTextPaddingResource(@DimenRes paddingRes: Int) {
        setTextPadding(resources.getDimensionPixelSize(paddingRes))
    }

    fun setImageDrawable(drawable: Drawable?) {
        tibImageLeft.setImageDrawable(drawable)
        tibImageRight.setImageDrawable(drawable)
    }

    fun setImageResource(@DrawableRes imageRes: Int) {
        setImageDrawable(context.resources.getDrawable(imageRes))
    }

    fun setImagePadding(padding: Int) {
        tibImageLeft.setPadding(padding, padding, padding, padding)
        tibImageRight.setPadding(padding, padding, padding, padding)
    }

    fun setImagePaddingResource(@DimenRes paddingRes: Int) {
        setImagePadding(resources.getDimensionPixelSize(paddingRes))
    }

    fun setImagePosition(imagePosition: ImagePosition) {
        when (imagePosition) {
            ImagePosition.START -> {
                tibImageLeft.visibility = View.VISIBLE
                tibImageRight.visibility = View.INVISIBLE
            }
            ImagePosition.END -> {
                tibImageLeft.visibility = View.INVISIBLE
                tibImageRight.visibility = View.VISIBLE
            }

        }
    }

    private fun setImagePosition(imagePositionValue: Int) {
        when (imagePositionValue) {
            1 -> setImagePosition(ImagePosition.END)
            else -> setImagePosition(ImagePosition.START)
        }
    }

    enum class ImagePosition(
            val value: Int
    ) {
        START(0), END(1)
    }

}