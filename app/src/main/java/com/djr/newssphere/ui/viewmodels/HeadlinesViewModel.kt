package com.djr.newssphere.ui.viewmodels

import androidx.lifecycle.*
import com.djr.newssphere.data.local.HeadlineDao
import com.djr.newssphere.data.model.Headline
import com.djr.newssphere.data.remote.NewsRepository
import kotlinx.coroutines.launch

class HeadlinesViewModel(
    private val newsRepository: NewsRepository,
    private val headlineDao: HeadlineDao
) : ViewModel() {
    private val _headlines = MutableLiveData<List<Headline>>()
    val headlines: LiveData<List<Headline>> = _headlines

    fun fetchTopHeadlines() {
        viewModelScope.launch {
            val cachedHeadlines = headlineDao.getAllHeadlines()

            if (cachedHeadlines.isNotEmpty()) {
                _headlines.value = cachedHeadlines
            }

            val freshHeadlines = newsRepository.fetchTopHeadlines()

            _headlines.value = freshHeadlines
        }
    }
}

