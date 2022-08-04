package com.example.newstestapp.feature_news.domain.news

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)