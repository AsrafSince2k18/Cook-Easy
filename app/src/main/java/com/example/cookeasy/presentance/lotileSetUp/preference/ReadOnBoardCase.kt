package com.example.cookeasy.presentance.lotileSetUp.preference

class ReadOnBoardCase(private val dataStoreRepository: DataStoreManager) {

    operator fun invoke() = dataStoreRepository.readOnBoard()
}