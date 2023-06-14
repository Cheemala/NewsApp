package com.cheemala.newsapp.presentation.di

import com.cheemala.newsapp.data.db.ArticleDao
import com.cheemala.newsapp.data.repository.datasource.NewsLocalDataSource
import com.cheemala.newsapp.data.repository.datasourceimpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(articleDao: ArticleDao): NewsLocalDataSource {
        return NewsLocalDataSourceImpl(articleDao)
    }
}