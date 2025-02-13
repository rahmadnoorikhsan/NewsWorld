package com.rahmad.newsworld.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rahmad.newsworld.data.repository.NewsRepository
import com.rahmad.newsworld.data.source.local.entity.NewsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun getNews(q: String) = newsRepository.getNews(q).asLiveData()

    fun insertNews(newsEntity: NewsEntity) = viewModelScope.launch {
        newsRepository.updateNews(newsEntity)
    }

    fun isReadNews(title: String) = newsRepository.isReadNews(title).asLiveData()
}