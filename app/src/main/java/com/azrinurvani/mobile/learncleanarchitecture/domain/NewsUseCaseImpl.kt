package com.azrinurvani.mobile.learncleanarchitecture.domain

import com.azrinurvani.mobile.learncleanarchitecture.data.Resource
import com.azrinurvani.mobile.learncleanarchitecture.data.source.NewsRepository
import com.azrinurvani.mobile.learncleanarchitecture.domain.model.News
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//TODO - Berfungsi untuk hanya memanggil repository yang dibutuhkan saja, tanpa implement semua method yang ada di repo
class NewsUseCaseImpl @Inject constructor(private val repository : NewsRepository) : NewsUseCase {

    override fun getNewsEverything(query: String): Flow<Resource<List<News>>> {
        return repository.getEverythingNews(query)
    }
}