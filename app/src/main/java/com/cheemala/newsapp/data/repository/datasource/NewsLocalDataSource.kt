package com.cheemala.newsapp.data.repository.datasource

import com.cheemala.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticle(article: Article)
    fun getAllNewsArticles(): Flow<List<Article>>
    suspend fun deleteArticle(article: Article)
}