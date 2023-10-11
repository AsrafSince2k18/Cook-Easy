package com.example.cookeasy.data.di

import com.example.cookeasy.data.repository.Repository
import com.example.cookeasy.data.useCase.CategoriesAndAreaCase
import com.example.cookeasy.data.useCase.DetailsScreenUSeCase
import com.example.cookeasy.data.useCase.MealByThingCase
import com.example.cookeasy.data.useCase.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideCategoriesAreaCase(repository: Repository)
    : CategoriesAndAreaCase{
        return CategoriesAndAreaCase(repository = repository)
    }

    @Provides
    @Singleton
    fun provideMealByThing(repository: Repository) : MealByThingCase{
        return MealByThingCase(repository=repository)
    }

    @Provides
    @Singleton
    fun provideDetailScreen(repository: Repository) : DetailsScreenUSeCase{
        return DetailsScreenUSeCase(repository=repository)
    }

    @Provides
    @Singleton
    fun provideSearchScreen(repository: Repository) : SearchUseCase{
        return SearchUseCase(repository=repository)
    }

}