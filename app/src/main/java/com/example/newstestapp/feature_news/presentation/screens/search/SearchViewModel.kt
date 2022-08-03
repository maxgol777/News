package com.example.newstestapp.feature_news.presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newstestapp.feature_news.data.remote.ArticleDto
import com.example.newstestapp.feature_news.domain.repository.NewsRepository
import com.example.newstestapp.feature_news.presentation.screens.search.pagination.DefaultPaginator
import com.example.newstestapp.feature_news.presentation.screens.search.pagination.Paginator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent: SharedFlow<String> = _errorEvent

    var state by mutableStateOf(ScreenState())
        private set

    var request: suspend (nextKey: Int) -> Result<List<ArticleDto>> = { nextPage ->
        repository.getNews(
            query = "",
            pageNumber = nextPage,
            pageSize = DEFAULT_PAGE_SIZE
        )
    }
    private val paginator: Paginator<Int, ArticleDto> = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = { state = state.copy(isLoading = it) },
        onRequest = { request(it) },
        getNextKey = {
            state.page + 1
        },
        onError = {
            viewModelScope.launch {
                _errorEvent.emit(it?.message ?: "Unknown error")
            }
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun createNewRequest(query: String) {
        state = ScreenState()
        paginator.reset()
        request = { nextPage ->
            repository.getNews(
                query = query,
                pageNumber = nextPage,
                pageSize = DEFAULT_PAGE_SIZE
            )
        }
        loadNextItems()
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 10
    }
}

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<ArticleDto> = emptyList(),
    val endReached: Boolean = false,
    val page: Int = 1
)