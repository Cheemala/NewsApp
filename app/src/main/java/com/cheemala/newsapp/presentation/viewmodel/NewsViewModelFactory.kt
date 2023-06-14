package com.cheemala.newsapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheemala.newsapp.domain.usecase.DeleteSavedNewsUseCase
import com.cheemala.newsapp.domain.usecase.GetNewsHeadlinesUseCase
import com.cheemala.newsapp.domain.usecase.GetSavedNewsUseCase
import com.cheemala.newsapp.domain.usecase.SaveNewsUseCase

class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsArticle: DeleteSavedNewsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app,getNewsHeadlinesUseCase,saveNewsUseCase,getSavedNewsUseCase,deleteSavedNewsArticle) as T
    }
}