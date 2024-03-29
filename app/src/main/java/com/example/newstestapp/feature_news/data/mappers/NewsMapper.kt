package com.example.newstestapp.feature_news.data.mappers

import com.example.newstestapp.feature_news.data.remote.ArticleDto
import com.example.newstestapp.feature_news.data.remote.NewsDto
import com.example.newstestapp.feature_news.data.remote.SourceDto
import com.example.newstestapp.feature_news.domain.news.Article
import com.example.newstestapp.feature_news.domain.news.News
import com.example.newstestapp.feature_news.domain.news.Source

fun NewsDto.toNews(): News = News(
    articles = articles.map { articleDto -> articleDto.toArticle() },
    status = status,
    totalResults
)

fun ArticleDto.toArticle(): Article =
    Article(
        author,
        content,
        description,
        publishedAt,
        source?.toSource(),
        title,
        url,
        imageUrl
    )

fun SourceDto.toSource(): Source = Source(id, name)