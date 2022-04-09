package com.example.newstestapp.feature_news.data.repository

import android.util.Log
import com.example.newstestapp.feature_news.data.network.NewsApi
import com.example.newstestapp.feature_news.domain.model.Article
import com.example.newstestapp.feature_news.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val api: NewsApi) : NewsRepository {
    override suspend fun getNews(
        query: String,
        pageNumber: Int,
        pageSize: Int,
    ): Result<List<Article>> = try {
        val articles = api.getNews(query = query, page = pageNumber, pageSize = pageSize).articles
        Result.success(articles)
    } catch (exception: Exception) {
        Log.d(TAG, "getNews: $exception")
        Result.failure(exception)
    }

    companion object {
        const val TAG = "NewsRepository"
    }
}