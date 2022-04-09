package com.example.newstestapp.feature_news.domain.model

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)