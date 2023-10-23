package com.azrinurvani.mobile.learncleanarchitecture.di

import android.util.Log
import com.azrinurvani.mobile.learncleanarchitecture.BuildConfig
import com.azrinurvani.mobile.learncleanarchitecture.helpers.CALL_TIMEOUT
import com.azrinurvani.mobile.learncleanarchitecture.helpers.CONNECT_TIMEOUT
import com.azrinurvani.mobile.learncleanarchitecture.helpers.READ_TIMEOUT
import com.azrinurvani.mobile.learncleanarchitecture.data.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule{

    @Provides
    fun provideOkHttpClient() : OkHttpClient{
        val interceptor = HttpLoggingInterceptor { message ->
            Log.d("News-API", "log: $message")
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(
                interceptor
            ).connectTimeout(
                timeout = CONNECT_TIMEOUT,
                TimeUnit.SECONDS
            )
            .readTimeout(
                timeout = READ_TIMEOUT,
                TimeUnit.SECONDS
            )
            .callTimeout(
                timeout = CALL_TIMEOUT,
                TimeUnit.SECONDS
            )
            .build()

    }

    @Provides
    fun provideRetrofitBuilder(httpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

}