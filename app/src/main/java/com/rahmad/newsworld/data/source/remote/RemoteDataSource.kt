package com.rahmad.newsworld.data.source.remote

import com.rahmad.newsworld.data.source.remote.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getNews(q: String) = apiService.getNews(q)
}