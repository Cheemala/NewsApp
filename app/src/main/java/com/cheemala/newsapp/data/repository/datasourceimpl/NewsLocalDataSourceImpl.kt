package com.cheemala.newsapp.data.repository.datasourceimpl

import com.cheemala.newsapp.data.db.ArticleDao
import com.cheemala.newsapp.data.model.Article
import com.cheemala.newsapp.data.repository.datasource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(private val articleDao: ArticleDao): NewsLocalDataSource {
    override suspend fun saveArticle(article: Article) {
        articleDao.saveArticle(article)
    }

    override fun getAllNewsArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles()
    }

    override suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(article)
    }
}