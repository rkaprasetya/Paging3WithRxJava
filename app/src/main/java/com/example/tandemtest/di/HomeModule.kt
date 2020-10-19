package com.example.tandemtest.di

import com.example.tandemtest.data.repositoryimpl.CommunityRepositoryImpl
import com.example.tandemtest.domain.repository.CommunityRepository
import com.example.tandemtest.domain.usecase.HomeUsecase
import com.example.tandemtest.domain.usecase.HomeUsecaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun provideHomeUsecase(usecase: HomeUsecaseImpl): HomeUsecase

    @Binds
    abstract fun provideHomeRepository(communityRepositoryImpl: CommunityRepositoryImpl): CommunityRepository

}