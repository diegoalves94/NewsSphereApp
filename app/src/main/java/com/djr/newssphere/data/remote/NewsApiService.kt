package com.djr.newssphere.data.remote

import com.djr.newssphere.data.model.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String
    ): Response<NewsApiResponse>
}