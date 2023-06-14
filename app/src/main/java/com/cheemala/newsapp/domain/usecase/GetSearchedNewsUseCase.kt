package com.cheemala.newsapp.domain.usecase

import com.cheemala.newsapp.data.model.NewsResponse
import com.cheemala.newsapp.data.util.Resource
import com.cheemala.newsapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(queryString: String): Resource<NewsResponse> = newsRepository.getSearchedNews(queryString)
}