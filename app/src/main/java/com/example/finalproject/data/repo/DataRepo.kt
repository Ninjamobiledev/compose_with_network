package com.example.finalproject.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.example.finalproject.Network.NetworkApi
import com.example.finalproject.data.DataSource
import retrofit2.http.Query
import javax.inject.Inject

class DataRepo @Inject constructor(val networkApi: NetworkApi) {
    fun getTrendingRepositories(searchQuery: String)= Pager(config = PagingConfig(10)){
      DataSource(networkApi, searchQuery = searchQuery)
    }.flow
}