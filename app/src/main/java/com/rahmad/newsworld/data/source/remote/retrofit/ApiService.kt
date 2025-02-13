package com.rahmad.newsworld.data.source.remote.retrofit

import com.rahmad.newsworld.data.source.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String
    ): NewsResponse
}