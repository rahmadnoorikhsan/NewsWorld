package com.rahmad.newsworld.data.repository

import com.rahmad.newsworld.data.source.local.LocalDataSource
import com.rahmad.newsworld.data.source.local.entity.NewsEntity
import com.rahmad.newsworld.data.source.remote.RemoteDataSource
import com.rahmad.newsworld.data.source.remote.retrofit.ApiResult
import com.rahmad.newsworld.domain.model.News
import com.rahmad.newsworld.utils.Constants
import com.rahmad.newsworld.utils.DataMappers.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NewsRepository {
    override fun getNews(q: String): Flow<ApiResult<List<News>>> = flow {
        emit(ApiResult.Loading())
        try {
            val response = remoteDataSource.getNews(q).articles.map { it.toDomain() }
            val news = response.toMutableList()
            val articlesWithHeader = mutableListOf<News>()

            for (i in news.indices) {
                if (i % 5 == 0) {
                    news[i].isHeader = true
                }
                articlesWithHeader.add(news[i])
            }

            emit(ApiResult.Success(articlesWithHeader))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(ApiResult.Error(e.message()))
        } catch (e: Exception) {
            emit(ApiResult.Error(Constants.ERROR_MESSAGE))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateNews(newsEntity: NewsEntity) = localDataSource.insertNews(newsEntity)

    override fun getHasBeenReadNews() = localDataSource.getHasBeenReadNews()

    override fun isReadNews(title: String) = localDataSource.isReadNews(title)
}