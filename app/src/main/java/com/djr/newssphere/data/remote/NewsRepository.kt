package com.djr.newssphere.data.remote

import com.djr.newssphere.data.local.HeadlineDao
import com.djr.newssphere.data.model.Headline
import com.djr.newssphere.utils.Constants.API_KEY
import com.djr.newssphere.utils.Constants.SOURCE

class NewsRepository(
    private val apiService: NewsApiService,
    private val headlineDao: HeadlineDao
) {
    suspend fun fetchTopHeadlines(): List<Headline> {
        // Make a network request to the News API using the NewsApiService
        val response = apiService.getTopHeadlines(API_KEY, SOURCE)

        // Save the response to the local database using the HeadlineDao
        headlineDao.insertHeadlines(response.body()!!.articles)

        // Return the list of headlines
        return response.body()!!.articles
    }
}