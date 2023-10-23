package com.azrinurvani.mobile.learncleanarchitecture.data.source

import com.azrinurvani.mobile.learncleanarchitecture.data.Resource
import com.azrinurvani.mobile.learncleanarchitecture.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getEverythingNews(query:String) : Flow<Resource<List<News>>>
}