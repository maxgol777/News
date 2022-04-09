package com.example.newstestapp.di

import com.example.newstestapp.feature_news.data.network.NewsApi
import com.example.newstestapp.feature_news.data.repository.NewsRepositoryImpl
import com.example.newstestapp.feature_news.data.network.NewsApiBuilder
import com.example.newstestapp.feature_news.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi = NewsApiBuilder.buildNewsApiInstance()

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi): NewsRepository = NewsRepositoryImpl(api)
}