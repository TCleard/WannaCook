package com.tcleard.wannacook.core.manager

import android.widget.ImageView

interface IImageManager {

    fun loadImage(url: String?, imageView: ImageView, onFinished: (() -> Unit)? = null): ImageRequest

    abstract class ImageRequest(
            var url: String?
    ) {
        abstract fun cancel()
    }

}