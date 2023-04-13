package com.example.finalproject.data.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.finalproject.data.repo.DataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(val repo:DataRepo): ViewModel() {
    val trendingRepos=repo.getTrendingRepositories("created:>=2023-03-10").cachedIn(viewModelScope)
}