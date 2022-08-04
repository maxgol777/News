package com.example.newstestapp.feature_news.data.mappers

import com.example.newstestapp.feature_news.data.remote.ArticleDto
import com.example.newstestapp.feature_news.data.remote.NewsDto
import com.example.newstestapp.feature_news.data.remote.SourceDto
import com.example.newstestapp.feature_news.domain.news.Article
import com.example.newstestapp.feature_news.domain.news.News
import com.example.newstestapp.feature_news.domain.news.Source

fun NewsDto.toNews(): News {

}

fun ArticleDto.toArticle(): Article {

}

fun SourceDto.toSource(): Source {

}