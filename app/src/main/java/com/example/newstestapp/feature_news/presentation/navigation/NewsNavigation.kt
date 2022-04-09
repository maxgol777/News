package com.example.newstestapp.feature_news.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newstestapp.feature_news.presentation.screens.detail.DetailScreen
import com.example.newstestapp.feature_news.presentation.screens.search.SearchScreen

@Composable
fun NewsNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NewsScreens.SearchScreen.name
    ) {
        composable(NewsScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(
            NewsScreens.DetailsScreen.name + "/{webpage}",
            arguments = listOf(navArgument(name = "webpage") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailScreen(
                navController = navController,
                articleUrl = backStackEntry.arguments?.getString("webpage")
            )
        }
    }
}