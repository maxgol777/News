package com.example.newstestapp.feature_news.presentation.screens.detail

import androidx.compose.runtime.Composable
import com.example.newstestapp.feature_news.presentation.screens.detail.components.WebPageViewer
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun DetailScreen(
    articleUrl: String? = null
) {
    articleUrl?.let {
        WebPageViewer(url = articleUrl)
    }
}

