package com.cheemala.newsapp.domain.usecase

import com.cheemala.newsapp.data.model.NewsResponse
import com.cheemala.newsapp.data.util.Resource
import com.cheemala.newsapp.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country:String, page:Int): Resource<NewsResponse> = newsRepository.getNewsHeadlines(country,page)
}