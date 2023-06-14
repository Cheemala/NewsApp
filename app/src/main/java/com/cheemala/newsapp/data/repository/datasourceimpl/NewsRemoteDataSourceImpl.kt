package com.cheemala.newsapp.data.repository.datasourceimpl

import com.cheemala.newsapp.data.model.NewsResponse
import com.cheemala.newsapp.data.network.NewsAPIService
import com.cheemala.newsapp.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService
) : NewsRemoteDataSource {
    override suspend fun getPopularNewsHeadLines(
        country: String,
        page: Int
    ): Response<NewsResponse> {
        return newsAPIService.getPopularNewsHeadLines(country, page)
    }

    override suspend fun getSearchedNews(): Response<NewsResponse> {
        TODO("Not yet implemented")
    }
}