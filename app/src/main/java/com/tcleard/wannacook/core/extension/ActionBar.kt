package com.tcleard.wannacook.core.extension

import com.tcleard.wannacook.R
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.ActionBar

fun ActionBar.getResources(): Resources = themedContext.resources

fun ActionBar.setHomeIcon(drawableRes: Int) {
    val size = getResources().getDimensionPixelSize(R.dimen.toolbarButtonSize)
    val drawable = getResources().getDrawable(drawableRes) as BitmapDrawable
    val resizedDrawable = Bitmap.createScaledBitmap(drawable.bitmap, size, size, false)
    setHomeAsUpIndicator(BitmapDrawable(getResources(), resizedDrawable))
    setDisplayHomeAsUpEnabled(true)
    setDisplayShowHomeEnabled(true)
}