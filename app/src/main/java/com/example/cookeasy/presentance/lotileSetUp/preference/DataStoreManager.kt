package com.example.cookeasy.presentance.lotileSetUp.preference

import kotlinx.coroutines.flow.Flow

interface DataStoreManager {

    suspend fun saveOnBoard()

    fun readOnBoard() : Flow<Boolean>

}