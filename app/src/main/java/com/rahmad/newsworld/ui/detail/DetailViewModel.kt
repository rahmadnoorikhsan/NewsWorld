package com.rahmad.newsworld.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahmad.newsworld.data.repository.NewsRepository
import com.rahmad.newsworld.data.source.local.entity.NewsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {


}