package com.cheemala.newsapp.data.network

import com.cheemala.newsapp.BuildConfig
import com.cheemala.newsapp.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("v2/top-headlines")
    suspend fun getPopularNewsHeadLines(@Query("country") country:String,@Query("page") page:Int,@Query("apiKey") apiKey:String = BuildConfig.API_KEY): Response<NewsResponse>
    @GET("v2/top-headlines")
    suspend fun getSearchedNews(@Query("q") searchQuery:String,@Query("apiKey") apiKey:String = BuildConfig.API_KEY): Response<NewsResponse>
}