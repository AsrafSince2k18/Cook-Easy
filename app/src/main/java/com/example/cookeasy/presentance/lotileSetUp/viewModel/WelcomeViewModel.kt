package com.example.cookeasy.presentance.lotileSetUp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookeasy.presentance.lotileSetUp.preference.ReadOnBoardCase
import com.example.cookeasy.presentance.screens.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val readOnBoardCase: ReadOnBoardCase) : ViewModel(){

    var splashCondition by mutableStateOf(true)
        private set

    var destination by mutableStateOf(Screens.WelcomeScreen.route)
        private set


    init {
        readOnBoardCase().onEach {startFromHome ->
            destination = if(startFromHome){
                Screens.HomeScreen.route
            } else {
                Screens.WelcomeScreen.route
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }




}