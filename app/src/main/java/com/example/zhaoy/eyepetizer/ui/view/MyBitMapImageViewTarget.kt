package com.example.zhaoy.eyepetizer.ui.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import java.lang.Exception

class MyBitMapImageViewTarget(view: ImageView) : BitmapImageViewTarget(view) {

    override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
        if (resource != null && view.scaleType != ImageView.ScaleType.FIT_XY) {
            view.scaleType = ImageView.ScaleType.FIT_XY
            view.setImageBitmap(resource)
        }
        super.onResourceReady(resource, glideAnimation)
    }

    override fun setResource(resource: Bitmap?) {
        super.setResource(resource)
    }

    override fun onLoadStarted(placeholder: Drawable?) {
        if (placeholder != null && view != null && view.scaleType != ImageView.ScaleType.CENTER_CROP) {
            view.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        super.onLoadStarted(placeholder)
    }

    override fun onLoadFailed(e: Exception?, errorDrawable: Drawable?) {
        if (errorDrawable != null && view != null && view.scaleType != ImageView.ScaleType.CENTER_CROP) {
            view.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        super.onLoadFailed(e, errorDrawable)
    }

    override fun onLoadCleared(placeholder: Drawable?) {
        if (placeholder != null && view != null && view.scaleType != ImageView.ScaleType.CENTER_CROP) {
            view.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        super.onLoadCleared(placeholder)
    }
}