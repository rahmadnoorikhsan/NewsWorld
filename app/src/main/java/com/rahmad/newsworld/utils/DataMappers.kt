package com.rahmad.newsworld.utils

import com.rahmad.newsworld.data.source.local.entity.NewsEntity
import com.rahmad.newsworld.data.source.remote.response.ArticlesItem
import com.rahmad.newsworld.domain.model.News

object DataMappers{
    fun ArticlesItem.toDomain() = News(
        publishedAt = publishedAt,
        author = author,
        urlToImage = urlToImage,
        description = description,
        source = source,
        title = title,
        url = url,
        content = content,
        isHeader = false
    )

    fun News.toEntity() = NewsEntity(
        title = title ?: "",
        publishedAt = publishedAt,
        urlToImage = urlToImage,
        url = url,
        isRead = true
    )
}