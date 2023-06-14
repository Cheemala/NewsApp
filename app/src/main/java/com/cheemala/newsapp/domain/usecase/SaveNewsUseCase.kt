package com.cheemala.newsapp.domain.usecase

import com.cheemala.newsapp.data.model.Article
import com.cheemala.newsapp.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.saveNewsArticle(article)
}