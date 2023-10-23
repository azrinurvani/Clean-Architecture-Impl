package com.azrinurvani.mobile.learncleanarchitecture.data.remote

import android.util.Log
import com.azrinurvani.mobile.learncleanarchitecture.BuildConfig
import com.azrinurvani.mobile.learncleanarchitecture.data.model.ResponseEverything
import com.azrinurvani.mobile.learncleanarchitecture.data.remote.network.ApiResource
import com.azrinurvani.mobile.learncleanarchitecture.data.remote.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
){
    suspend fun getEverythingNews(query : String) :Flow<ApiResource<ResponseEverything>> = flow {
        try {
            emit(
                ApiResource.Success(
                    apiService.getEverythingNews(
                        apiKey = BuildConfig.API_KEY,
                        query = query,
                        pageSize = 5,
                        page = 1
                    )
                )
            )
        }catch (e : Throwable){
            emit(ApiResource.Error(e))
            Log.e(TAG, "getEverythingNews: error ${e.message}", )
        }
    }

    companion object {
        private const val TAG = "RemoteDataSource"
    }
}