package com.rahmad.newsworld.di

import android.content.Context
import androidx.room.Room
import com.rahmad.newsworld.data.source.local.room.NewsDao
import com.rahmad.newsworld.data.source.local.room.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMovieDao(movieDatabase: NewsDatabase): NewsDao =
        movieDatabase.getMovieDao()
}