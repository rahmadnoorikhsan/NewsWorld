package com.rahmad.newsworld.data.source.local

import com.rahmad.newsworld.data.source.local.entity.NewsEntity
import com.rahmad.newsworld.data.source.local.room.NewsDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val newsDao: NewsDao
) {

    fun getHasBeenReadNews() = newsDao.getHasBeenReadNews()

    suspend fun insertNews(newsEntity: NewsEntity) = newsDao.insertNews(newsEntity)

    fun isReadNews(title: String) = newsDao.isReadNews(title)
}