package com.cheemala.newsapp.data.repository

import com.cheemala.newsapp.data.model.Article
import com.cheemala.newsapp.data.model.NewsResponse
import com.cheemala.newsapp.data.repository.datasource.NewsLocalDataSource
import com.cheemala.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.cheemala.newsapp.data.util.Resource
import com.cheemala.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(private val newsRemoteDataSource: NewsRemoteDataSource,
private val newsLocalDataSource: NewsLocalDataSource):NewsRepository {
    override suspend fun getNewsHeadlines(country: String,page: Int): Resource<NewsResponse> {
        return convertJsonResponseToResourceResponse(newsRemoteDataSource.getPopularNewsHeadLines(country,page))
    }

    override suspend fun getSearchedNews(queryString: String): Resource<NewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNewsArticle(article: Article) {
        newsLocalDataSource.saveArticle(article)
    }

    override suspend fun deleteNewsArticle(article: Article) {
        newsLocalDataSource.deleteArticle(article)
    }

    override fun getSavedNewsArticles(): Flow<List<Article>> {
        return newsLocalDataSource.getAllNewsArticles()
    }

    private fun convertJsonResponseToResourceResponse(jsonResponse: Response<NewsResponse>):Resource<NewsResponse>{
        if (jsonResponse.isSuccessful){
            jsonResponse.body()?.let {result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(jsonResponse.message())
    }
}