package com.azrinurvani.mobile.learncleanarchitecture.domain

import com.azrinurvani.mobile.learncleanarchitecture.data.Resource
import com.azrinurvani.mobile.learncleanarchitecture.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {

    fun getNewsEverything(query : String) : Flow<Resource<List<News>>>
}