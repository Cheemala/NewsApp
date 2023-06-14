package com.cheemala.newsapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.cheemala.newsapp.data.model.Article
import com.cheemala.newsapp.data.model.NewsResponse
import com.cheemala.newsapp.data.util.Resource
import com.cheemala.newsapp.domain.usecase.DeleteSavedNewsUseCase
import com.cheemala.newsapp.domain.usecase.GetNewsHeadlinesUseCase
import com.cheemala.newsapp.domain.usecase.GetSavedNewsUseCase
import com.cheemala.newsapp.domain.usecase.SaveNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsArticle: DeleteSavedNewsUseCase
) : AndroidViewModel(app) {

    val topNewsHeadlines: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    fun getNewsHeadlines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        topNewsHeadlines.postValue(Resource.Loading())
        try {
            if (!isNetworkAvailable()) {
                topNewsHeadlines.postValue(Resource.Error("Please connect to Internet!"))
            } else {
                val apiResult = getNewsHeadlinesUseCase.execute(country, page)
                topNewsHeadlines.postValue(apiResult)
            }
        } catch (ex: Exception) {
            topNewsHeadlines.postValue(Resource.Error(ex.message.toString()))
        }
    }

    // save to local db
    fun saveNewsArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        saveNewsUseCase.execute(article)
    }

    fun getSavedNewsArticles() = liveData {
        getSavedNewsUseCase.execute().collect{
            emit(it)
        }
    }

    fun deleteSavedNewsArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        deleteSavedNewsArticle.execute(article)
    }

    private fun isNetworkAvailable(): Boolean = true

}