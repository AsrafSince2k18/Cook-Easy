package com.example.cookeasy.presentance.lotileSetUp.preference

class SaveOnBoardCase (private val dataStoreManger : DataStoreManager){

    suspend operator fun invoke() = dataStoreManger.saveOnBoard()

}