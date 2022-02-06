package com.example.truemedstask.network

import com.example.truemedstask.model.ArticleResponse
import com.example.truemedstask.utils.Constants
import retrofit2.Call
import retrofit2.http.POST

interface NetworkAPI {
    @POST(Constants.ARTICLE_URL)
    suspend fun getAllArticle(): Call<ArticleResponse>

}