package com.djr.newssphere.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.djr.newssphere.data.local.HeadlineDao
import com.djr.newssphere.data.remote.NewsRepository

class HeadlinesViewModelFactory(
    private val repository: NewsRepository,
    private val headlineDao: HeadlineDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeadlinesViewModel::class.java)) {
            return HeadlinesViewModel(repository, headlineDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}