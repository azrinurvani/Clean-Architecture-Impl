package com.azrinurvani.mobile.learncleanarchitecture.di

import com.azrinurvani.mobile.learncleanarchitecture.data.NewsRepositoryImpl
import com.azrinurvani.mobile.learncleanarchitecture.data.source.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        NetworkModule::class
    ]
)
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepository(repositoryImpl: NewsRepositoryImpl) : NewsRepository

}