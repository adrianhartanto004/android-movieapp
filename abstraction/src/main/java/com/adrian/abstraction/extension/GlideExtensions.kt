package com.adrian.abstraction.extension

import android.widget.ImageView
import com.adrian.abstraction.common.network.constant.UrlProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


fun ImageView.loadImageUrl(url: String?, options: RequestOptions? = null) {
    if (options != null) {
        Glide.with(context)
            .load(UrlProvider.BASE_IMAGE_URL + url)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .apply(options)
            .into(this)
    } else {
        Glide.with(context)
            .load(UrlProvider.BASE_IMAGE_URL + url)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(this)
    }
}