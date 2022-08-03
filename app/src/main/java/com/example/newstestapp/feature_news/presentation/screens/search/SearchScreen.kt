package com.example.newstestapp.feature_news.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newstestapp.R
import com.example.newstestapp.feature_news.presentation.screens.search.components.ArticleRow
import com.example.newstestapp.feature_news.presentation.screens.search.components.CommonTextField
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
@RootNavGraph(start = true)
@Destination
fun SearchScreen(
    navigator: DestinationsNavigator,
    newsViewModel: NewsViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val state = newsViewModel.state
    LaunchedEffect(key1 = true) {
        newsViewModel.errorEvent.collect {
            scaffoldState.snackbarHostState.showSnackbar(message = it)
        }
    }

    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value, searchQueryState.value.trim()::isNotEmpty)

    Scaffold(scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    backgroundColor = Black,
                    contentColor = Red,
                    snackbarData = data
                )
            }
        }
    ) {
        Column(Modifier.fillMaxSize()) {
            CommonTextField(
                valueState = searchQueryState,
                placeholder = stringResource(R.string.search_placeholder_title),
                onAction = KeyboardActions {
                    if (!valid) {
                        return@KeyboardActions
                    } else {
                        newsViewModel.createNewRequest(searchQueryState.value.trim())
                        searchQueryState.value = ""
                        keyboardController?.hide()
                    }
                })
            LazyColumn(modifier = Modifier.fillMaxSize())
            {
                items(state.items.size) { i ->
                    val article = state.items[i]
                    if (doNeedToLoad(i, state = state)) {
                        newsViewModel.loadNextItems()
                    }
                    ArticleRow(article, navigator)
                }
                item {
                    if (state.isLoading) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

fun doNeedToLoad(i: Int, state: ScreenState): Boolean =
    (i >= state.items.size - 1) && !state.endReached && !state.isLoading