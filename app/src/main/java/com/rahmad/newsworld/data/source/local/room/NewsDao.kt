package com.rahmad.newsworld.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rahmad.newsworld.data.source.local.entity.NewsEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(news: NewsEntity)

    @Query("SELECT * FROM news where isRead = 1")
    fun getHasBeenReadNews(): LiveData<List<NewsEntity>>
}