package com.azrinurvani.mobile.learncleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.azrinurvani.mobile.learncleanarchitecture.domain.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel(){

    fun getNewsEverything(query:String) = newsUseCase.getNewsEverything(query).asLiveData()

}