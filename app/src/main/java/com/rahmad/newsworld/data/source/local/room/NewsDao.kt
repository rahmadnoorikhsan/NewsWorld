package com.rahmad.newsworld.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rahmad.newsworld.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news ORDER BY publishedAt DESC")
    fun getNews(): LiveData<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(newsEntity: NewsEntity)

    @Query("SELECT * FROM news where isRead = 1")
    fun getHasBeenReadNews(): LiveData<List<NewsEntity>>

    @Query("SELECT EXISTS(SELECT * FROM news WHERE title = :title AND isRead = 1)")
    fun isReadNews(title: String): Flow<Boolean>
}