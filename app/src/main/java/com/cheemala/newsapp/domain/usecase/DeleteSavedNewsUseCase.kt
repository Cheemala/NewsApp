package com.cheemala.newsapp.domain.usecase

import com.cheemala.newsapp.data.model.Article
import com.cheemala.newsapp.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.deleteNewsArticle(article)
}