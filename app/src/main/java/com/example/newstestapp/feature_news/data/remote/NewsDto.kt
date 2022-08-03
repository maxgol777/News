package com.example.newstestapp.feature_news.data.remote

data class NewsDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)