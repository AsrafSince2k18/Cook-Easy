package com.example.cookeasy.presentance.lotileSetUp.di

import android.content.Context
import com.example.cookeasy.presentance.lotileSetUp.preference.DataStoreRepository
import com.example.cookeasy.presentance.lotileSetUp.preference.ReadOnBoardCase
import com.example.cookeasy.presentance.lotileSetUp.preference.DataStoreManager
import com.example.cookeasy.presentance.lotileSetUp.preference.SaveOnBoardCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object lottieModule {

    @Provides
    @Singleton
    fun provideDataStoreManager(
        @ApplicationContext
        appContext: Context
    ): DataStoreManager = DataStoreRepository(appContext)


    @Provides
    @Singleton
    fun provideReadOnBoardUseCase(
        dataStoreManager: DataStoreManager
    ) = ReadOnBoardCase(dataStoreManager)

    @Provides
    @Singleton
    fun provideSaveOnBoardUseCase(
        dataStoreManager: DataStoreManager
    ) = SaveOnBoardCase(dataStoreManager)


}