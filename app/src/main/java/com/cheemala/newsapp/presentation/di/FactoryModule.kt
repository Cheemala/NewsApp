package com.cheemala.newsapp.presentation.di

import android.app.Application
import com.cheemala.newsapp.domain.usecase.DeleteSavedNewsUseCase
import com.cheemala.newsapp.domain.usecase.GetNewsHeadlinesUseCase
import com.cheemala.newsapp.domain.usecase.GetSavedNewsUseCase
import com.cheemala.newsapp.domain.usecase.SaveNewsUseCase
import com.cheemala.newsapp.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsVMFactory(application: Application,newsHeadlinesUseCase: GetNewsHeadlinesUseCase,saveNewsUseCase: SaveNewsUseCase,getSavedNewsUseCase: GetSavedNewsUseCase,deleteSavedNewsArticle: DeleteSavedNewsUseCase):NewsViewModelFactory{
        return NewsViewModelFactory(application,newsHeadlinesUseCase,saveNewsUseCase,getSavedNewsUseCase,deleteSavedNewsArticle)
    }

}