package com.tcleard.wannacook.core.manager.impl

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.tcleard.wannacook.core.manager.IImageManager
import javax.inject.Inject

class GlideImageManager @Inject constructor(
        private val context: Context
) : IImageManager {

    private val glide
        get() = Glide.with(context)

    override fun loadImage(url: String?, imageView: ImageView, onFinished: (() -> Unit)?): IImageManager.ImageRequest {
        glide.load(url)
                .apply(RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .listener(object : RequestListener<Drawable> {

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        onFinished?.invoke()
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        onFinished?.invoke()
                        return false
                    }
                })
                .into(imageView)
        return GlideImageRequest(imageView, url)
    }

    inner class GlideImageRequest(
            private val imageView: ImageView,
            url: String?
    ) : IImageManager.ImageRequest(url) {

        override fun cancel() {
            glide.clear(imageView)
        }

    }

}