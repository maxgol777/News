package com.example.newstestapp.feature_news.presentation.screens.search.pagination

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}