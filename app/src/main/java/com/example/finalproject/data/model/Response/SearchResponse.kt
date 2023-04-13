package com.example.finalproject.data.model.Response

import com.example.finalproject.data.model.GithubRepository
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(val total_count: Int,val items:List<GithubRepository>)