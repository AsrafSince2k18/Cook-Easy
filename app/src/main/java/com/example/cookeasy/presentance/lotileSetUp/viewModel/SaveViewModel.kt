package com.example.cookeasy.presentance.lotileSetUp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookeasy.presentance.lotileSetUp.preference.SaveOnBoardCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(
    private val saveOnBoardCase: SaveOnBoardCase
): ViewModel() {

    fun saveOnBoardCases(){
        viewModelScope.launch(Dispatchers.IO) {
            saveOnBoardCase()
        }
    }

}