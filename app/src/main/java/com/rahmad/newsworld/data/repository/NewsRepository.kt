package com.rahmad.newsworld.data.repository

import androidx.lifecycle.LiveData
import com.rahmad.newsworld.data.source.local.entity.NewsEntity
import com.rahmad.newsworld.data.source.remote.retrofit.ApiResult
import com.rahmad.newsworld.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(q: String): Flow<ApiResult<List<News>>>
    suspend fun insertNews(newsEntity: NewsEntity)
    fun getHasBeenReadNews(): LiveData<List<NewsEntity>>
}