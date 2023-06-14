package com.cheemala.newsapp.domain.repository

import com.cheemala.newsapp.data.model.Article
import com.cheemala.newsapp.data.model.NewsResponse
import com.cheemala.newsapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadlines(country: String, page: Int) : Resource<NewsResponse>
    suspend fun getSearchedNews(queryString: String): Resource<NewsResponse>
    suspend fun saveNewsArticle(article: Article)
    suspend fun deleteNewsArticle(article: Article)
    fun getSavedNewsArticles(): Flow<List<Article>>
}