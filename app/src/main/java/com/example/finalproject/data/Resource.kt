package com.example.finalproject.data

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val loadingStatus: Boolean?=null
) {

    class SUCCESS<T>(data: T) : Resource<T>(data)
    class ERROR<T>( message: String,data: T? = null,) : Resource<T>(data, message)
    class LOADING<T>(loadingStatus: Boolean) :Resource<T>(loadingStatus=loadingStatus)
}