package com.cheemala.newsapp.presentation.di

import com.cheemala.newsapp.data.network.NewsAPIService
import com.cheemala.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.cheemala.newsapp.data.repository.datasourceimpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideRemoteDatasource(newsAPIService: NewsAPIService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsAPIService)
    }

}