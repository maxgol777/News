package com.example.newstestapp.feature_news.presentation.screens.detail

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.newstestapp.feature_news.presentation.screens.detail.components.WebPageViewer

@Composable
fun DetailScreen(
    navController: NavController,
    articleUrl: String? = null
) {
    articleUrl?.let {
        WebPageViewer(url = articleUrl)
    }
}

