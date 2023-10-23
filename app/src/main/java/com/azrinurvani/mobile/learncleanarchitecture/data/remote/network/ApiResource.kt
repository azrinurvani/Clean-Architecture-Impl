package com.azrinurvani.mobile.learncleanarchitecture.data.remote.network

sealed class ApiResource<out R> private constructor(){
    data class Success<out T>(val data: T) : ApiResource<T>()
    data class Error(val error : Throwable) :ApiResource<Nothing>()
}