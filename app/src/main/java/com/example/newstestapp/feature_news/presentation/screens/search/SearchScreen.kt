package com.example.newstestapp.feature_news.presentation.screens.search

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newstestapp.R
import com.example.newstestapp.feature_news.presentation.screens.search.components.ArticleRow
import com.example.newstestapp.feature_news.presentation.screens.search.components.CommonTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = newsViewModel.state
    state.error?.let { showToast(LocalContext.current, it) }
    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value, searchQueryState.value.trim()::isNotEmpty)

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
                ArticleRow(article, navController)
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

fun showToast(context: Context, message: String) = Toast.makeText(
    context,
    message,
    Toast.LENGTH_SHORT
).show()

fun doNeedToLoad(i: Int, state: ScreenState): Boolean =
    (i >= state.items.size - 1) && !state.endReached && !state.isLoading