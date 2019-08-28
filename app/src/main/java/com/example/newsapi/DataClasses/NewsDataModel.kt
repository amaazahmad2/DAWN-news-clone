package com.example.newsapi.DataClasses

data class NewsDataModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)