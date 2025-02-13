package com.rahmad.newsworld.utils

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.rahmad.newsworld.R

object Extensions {
    fun ImageView.showImageInto(context: Context, url: String?) {
        Glide.with(context)
            .load(url)
            .placeholder(ContextCompat.getDrawable(context, R.color.shimmer_color))
            .into(this)
    }
}