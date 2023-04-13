package com.example.finalproject.Network

import com.example.finalproject.data.model.GithubRepository
import com.example.finalproject.data.model.Response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    @GET("search/repositories")
    suspend fun getTrendingRepos(@Query("q")searchQuery:String,
                                 @Query("order")sortOrder:String,
                                 @Query("page")page:Int,
                                 @Query("per_page")pageSize:Int):Response<SearchResponse>


}