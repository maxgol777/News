package com.example.newstestapp.feature_news.data.remote
import com.squareup.moshi.Json

data class ArticleDto(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceDto? = null,
    val title: String?,
    val url: String?,
    @field:Json(name = "urlToImage")
    val imageUrl: String?
)