package com.rahmad.newsworld.ui.history

import androidx.lifecycle.ViewModel
import com.rahmad.newsworld.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun getHasBeenReadNews() = newsRepository.getHasBeenReadNews()
}