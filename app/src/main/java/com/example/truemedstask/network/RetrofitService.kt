package com.example.truemedstask.network

import com.example.truemedstask.model.ArticleResponse
import com.example.truemedstask.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

interface RetrofitService {
    @POST(Constants.ARTICLE_URL)
    suspend fun getAllArticle(): Response<ArticleResponse>


    companion object {
        var interceptor = HttpLoggingInterceptor()
        init {

            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        var client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {
            if (RetrofitService.retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                RetrofitService.retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return RetrofitService.retrofitService!!
        }

    }
}
