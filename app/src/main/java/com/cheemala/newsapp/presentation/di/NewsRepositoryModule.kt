package com.cheemala.newsapp.presentation.di

import com.cheemala.newsapp.data.repository.NewsRepositoryImpl
import com.cheemala.newsapp.data.repository.datasource.NewsLocalDataSource
import com.cheemala.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.cheemala.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NewsRepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(newsRemoteDataSource: NewsRemoteDataSource, newsLocalDataSource: NewsLocalDataSource):NewsRepository{
        return NewsRepositoryImpl(newsRemoteDataSource,newsLocalDataSource)
    }

}