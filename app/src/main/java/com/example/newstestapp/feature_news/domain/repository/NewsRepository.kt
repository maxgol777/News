package com.example.newstestapp.feature_news.domain.repository

import com.example.newstestapp.feature_news.domain.model.Article

interface NewsRepository {
    suspend fun getNews(
        query: String,
        pageNumber: Int,
        pageSize: Int = 100
    ): Result<List<Article>>
}