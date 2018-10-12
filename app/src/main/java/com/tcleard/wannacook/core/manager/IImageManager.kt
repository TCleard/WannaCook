package com.tcleard.wannacook.core.manager

import android.widget.ImageView

interface IImageManager {

    fun loadImage(url: String?, imageView: ImageView): ImageRequest

    abstract class ImageRequest(
            var url: String?
    ) {
        abstract fun cancel()
    }

}