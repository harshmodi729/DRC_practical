package com.harsh.drc_practical.data

import com.harsh.drc_practical.model.Article
import retrofit2.http.GET
import retrofit2.http.Query

object APICONSTANT {
    const val HEADLINES = "top-headlines"
}

interface ApiInterface {

    //    https://newsapi.org/v2/top-headlines?sources=google-news&apiKey=9a0c8e375ada4198a26f7a52638c4b78
    @GET(APICONSTANT.HEADLINES)
    suspend fun getHeadLines(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String
    ): ApiResponse<ArrayList<Article>>
}