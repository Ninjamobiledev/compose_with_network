package com.example.finalproject.Network

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.finalproject.Constants
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.time.Duration
import javax.inject.Named
import javax.inject.Scope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Named("AuthClient")
    fun provideAuthInterceptorOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor).also {
                if(Constants.DEBUG){
                    it.addInterceptor(provideLoggingInterceptors())
                }
            }
            .connectTimeout(Duration.ofSeconds(Constants.TIMEOUT))
            .readTimeout(Duration.ofSeconds(Constants.TIMEOUT))
            .writeTimeout(Duration.ofSeconds(Constants.TIMEOUT))
            .retryOnConnectionFailure(true)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Named("OtherClient")
    fun provideOtherInterceptorOkHttpClient(
        otherInterceptor: OtherInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(otherInterceptor).also {
                if(Constants.DEBUG){
                    it.addInterceptor(provideLoggingInterceptors())
                }
            }
            .connectTimeout(Duration.ofSeconds(Constants.TIMEOUT))
            .readTimeout(Duration.ofSeconds(Constants.TIMEOUT))
            .writeTimeout(Duration.ofSeconds(Constants.TIMEOUT))
            .retryOnConnectionFailure(true)
            .build()
    }
    @MediaInterceptorOkHttpClient
    @Provides
    @Named("MediaClient")
    fun provideMediaInterceptorOkHttpClient(
        mediaInterceptor: MediaInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(mediaInterceptor).retryOnConnectionFailure(true).also {
                if(Constants.DEBUG){
                    it.addInterceptor(provideLoggingInterceptors())
                }
            }
            .build()
    }
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
    @Provides
    @Singleton
    fun provideLoggingInterceptors(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(HttpLoggingInterceptor.Level.BASIC)
    }
    @Provides
    @Singleton
    @Named("OtherRetrofit")
    fun provideRetrofit(@Named("OtherClient")okHttpClient: OkHttpClient,moshi: Moshi):Retrofit{
      return Retrofit.Builder()
          .baseUrl(Constants.BASE_URL)
          .client(okHttpClient)
          .addConverterFactory(MoshiConverterFactory.create(moshi))
          .build()
    }
    @Provides
    @Singleton
    @Named("AuthRetrofit")
    fun provideAuthRetrofit(@Named("AuthClient")okHttpClient: OkHttpClient,moshi: Moshi):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    @Provides
    fun provideNetworkApi(@Named("OtherRetrofit")retrofit: Retrofit):NetworkApi{
        return retrofit.create(NetworkApi::class.java)
    }

}


@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class AuthInterceptorOkHttpClient

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class OtherInterceptorOkHttpClient

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class MediaInterceptorOkHttpClient
