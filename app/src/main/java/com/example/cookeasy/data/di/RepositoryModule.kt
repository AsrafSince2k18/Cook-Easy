package com.example.cookeasy.data.di

import com.example.cookeasy.data.apiInterface.ApiServices
import com.example.cookeasy.data.domain.RepositoryImpl
import com.example.cookeasy.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideRepository(apiServices: ApiServices) : Repository{
        return RepositoryImpl(apiServices = apiServices)
    }
}