package com.azrinurvani.mobile.learncleanarchitecture.di

import com.azrinurvani.mobile.learncleanarchitecture.domain.NewsUseCase
import com.azrinurvani.mobile.learncleanarchitecture.domain.NewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsNewsUseCase(newsUseCaseImpl : NewsUseCaseImpl) : NewsUseCase
}