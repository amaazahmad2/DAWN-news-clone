package com.example.newsapi


import com.example.newsapi.DataClasses.NewsDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface
{
    @GET("/v2/top-headlines")
    fun getTopHeadlines(@Query("apiKey")apiKey:String,
                        @Query("sources")sources:String): Call<NewsDataModel>

    @GET("/v2/everything")
    fun getLatest(@Query("apiKey")apiKey:String,
                      @Query("sortBy")sortBy:String,
                      @Query("language")language:String,
                        @Query("sources")sources:String): Call<NewsDataModel>

    @GET("/v2/everything")
    fun getPakistani(@Query("apiKey")apiKey:String,
                     @Query("language")language:String,
                     @Query("sources")sources:String): Call<NewsDataModel>

    @GET("/v2/top-headlines")
    fun getEntertainment(@Query("apiKey")apiKey:String,
                         @Query("sources")sources:String): Call<NewsDataModel>

    @GET("/v2/top-headlines")
    fun getOpinion(@Query("apiKey")apiKey:String,
                   @Query("category")category:String): Call<NewsDataModel>
}