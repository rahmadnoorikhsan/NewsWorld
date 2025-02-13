package com.rahmad.newsworld.domain.model

import android.os.Parcelable
import com.rahmad.newsworld.data.source.remote.response.Source
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val content: String? = null,
    var isHeader: Boolean = false,
    var isRead: Boolean = false
) : Parcelable
