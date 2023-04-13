package com.example.finalproject.Network


import com.example.finalproject.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request=chain.request()
        try{
           val newRequest=request.newBuilder()
               .addHeader("Authorization", Constants.AUTH).addHeader("Accept", "application/json;")
               .build()
            return chain.proceed(newRequest)
        }
        catch (e:Exception){
           return chain.proceed(request)
        }

    }

}

class OtherInterceptor @Inject constructor():Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request=chain.request()
        try{
            val newRequest=request.newBuilder()
                .build()
            return chain.proceed(newRequest)
        }
        catch (e:Exception){
            return chain.proceed(request)
        }

    }


}
class MediaInterceptor @Inject constructor():Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        try {
            val newRequest = request.newBuilder()
                .build()
            return chain.proceed(newRequest)
        } catch (e: Exception) {
            return chain.proceed(request)
        }


    }
}