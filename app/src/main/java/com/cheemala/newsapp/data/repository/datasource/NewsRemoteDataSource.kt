package com.cheemala.newsapp.data.repository.datasource

import com.cheemala.newsapp.data.model.NewsResponse
import com.cheemala.newsapp.data.network.NewsAPIService
import retrofit2.Response
import retrofit2.http.Query

interface NewsRemoteDataSource {
    suspend fun getPopularNewsHeadLines(
        country: String,
        page: Int
    ): Response<NewsResponse>

    suspend fun getSearchedNews(): Response<NewsResponse>
}