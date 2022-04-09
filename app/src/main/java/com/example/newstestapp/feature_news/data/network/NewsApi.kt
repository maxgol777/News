package com.example.newstestapp.feature_news.data.network

import com.example.newstestapp.feature_news.data.utils.Constants
import com.example.newstestapp.feature_news.data.utils.Util
import com.example.newstestapp.feature_news.domain.model.News
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NewsApi {

    @GET(value = "v2/everything")
    suspend fun getNews(
        @Query("q") query: String = "",
        @Query("from") from: String = Util.currentDate(),
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): News
}