package com.azrinurvani.mobile.learncleanarchitecture.data.remote.network

import com.azrinurvani.mobile.learncleanarchitecture.data.model.ResponseEverything
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/everything")
    suspend fun getEverythingNews(
        @Query("apiKey") apiKey : String? = null,
        @Query("q") query : String?,
        @Query("pageSize") pageSize : Int? = null,
        @Query("page") page : Int? = null
    ) : ResponseEverything


}