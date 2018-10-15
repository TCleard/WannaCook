package com.tcleard.wannacook.core.extension

import android.support.v4.graphics.ColorUtils

fun Int.withAlpha(alpha: Float): Int =
        ColorUtils.setAlphaComponent(this, (alpha * 255).toInt())