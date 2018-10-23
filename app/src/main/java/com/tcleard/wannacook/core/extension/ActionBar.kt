package com.tcleard.wannacook.core.extension

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.ActionBar
import com.tcleard.wannacook.R

fun ActionBar.getResources(): Resources = themedContext.resources

fun ActionBar.setHomeIcon(drawableRes: Int) {
    val size = getResources().getDimensionPixelSize(R.dimen.toolbarIconSize)
    val bitmap = (getResources().getDrawable(drawableRes) as BitmapDrawable).bitmap
    val drawable = BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, size, size, true))
    drawable.setColorFilter(getResources().getColor(R.color.dark), PorterDuff.Mode.SRC_IN)
    setHomeAsUpIndicator(drawable)
    setDisplayHomeAsUpEnabled(true)
    setDisplayShowHomeEnabled(true)
}