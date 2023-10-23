package com.azrinurvani.mobile.learncleanarchitecture.data

import android.util.Log
import com.azrinurvani.mobile.learncleanarchitecture.data.remote.RemoteDataSource
import com.azrinurvani.mobile.learncleanarchitecture.data.remote.network.ApiResource
import com.azrinurvani.mobile.learncleanarchitecture.data.source.NewsRepository
import com.azrinurvani.mobile.learncleanarchitecture.domain.model.News
import com.azrinurvani.mobile.learncleanarchitecture.helpers.mapperResponseToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : NewsRepository{
    override fun getEverythingNews(query: String): Flow<Resource<List<News>>> = flow {
        emit(Resource.Loading())
        try{
            when(val response = remoteDataSource.getEverythingNews(query).single()){
                is ApiResource.Error ->{
                    emit(Resource.Error(message = response.error.toString()))
                }
                is ApiResource.Success ->{
                    //mapping data to domain to easy when using mock data for testing
                    emit(Resource.Success(mapperResponseToDomain(response.data)))
                }
            }
        }catch (e : Throwable){
            Log.e(javaClass.name, "getEverythingNews: ${e.message}")
        }
    }

}