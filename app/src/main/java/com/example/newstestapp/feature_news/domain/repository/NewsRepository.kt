package com.example.newstestapp.feature_news.domain.repository

import com.example.newstestapp.feature_news.domain.news.News

interface NewsRepository {
    suspend fun getNews(
        query: String,
        pageNumber: Int,
        pageSize: Int = 100
    ): Result<News>
}