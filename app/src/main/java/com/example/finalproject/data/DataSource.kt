package com.example.finalproject.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.finalproject.Network.NetworkApi
import com.example.finalproject.data.model.GithubRepository
import javax.inject.Inject
import javax.inject.Named

class DataSource(val networkApi: NetworkApi, val searchQuery:String): PagingSource<Int, GithubRepository>() {

    override fun getRefreshKey(state: PagingState<Int, GithubRepository>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)?:
            state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepository> {
        return try{
            val page=params.key?:0
            val response=networkApi.getTrendingRepos(searchQuery,"desc",page,params.loadSize)
            if(response.body()!=null){
                response.body()!!.items.forEach {
                    Log.e("error",""+it)
                }
                LoadResult.Page(response.body()!!.items, prevKey = if(page==1)null else page-1,
                    nextKey = page+1)
            }
            else{
                LoadResult.Error(CustomException("Response is empty"))
            }




        }
        catch (e:Exception){
            Log.e("error","exception ocurred"+e)
          LoadResult.Error(e)
        }

    }

    class CustomException(msg:String) : Exception(msg)
}