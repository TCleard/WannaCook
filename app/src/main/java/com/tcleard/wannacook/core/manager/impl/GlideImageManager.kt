package com.tcleard.wannacook.core.manager.impl

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.manager.IImageManager
import javax.inject.Inject

class GlideImageManager @Inject constructor(
        private val context: Context
) : IImageManager {

    private val glide
        get() = Glide.with(context)

    override fun loadImage(url: String?, imageView: ImageView): IImageManager.ImageRequest {
        glide.load(url)
                .into(imageView)
        return GlideImageRequest(imageView, url)
    }

    inner class GlideImageRequest(
            private val imageView: ImageView,
            url: String?
    ) : IImageManager.ImageRequest(url) {

        override fun cancel() {
            glide.clear(imageView)
            imageView.setImageResource(R.color.transparent)
        }

    }

}