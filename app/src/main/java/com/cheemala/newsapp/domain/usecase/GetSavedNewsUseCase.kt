package com.cheemala.newsapp.domain.usecase

import com.cheemala.newsapp.data.model.Article
import com.cheemala.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    fun execute(): Flow<List<Article>> = newsRepository.getSavedNewsArticles()
}