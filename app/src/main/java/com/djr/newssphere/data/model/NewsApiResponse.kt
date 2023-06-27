package com.djr.newssphere.data.model

data class NewsApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Headline>
)
