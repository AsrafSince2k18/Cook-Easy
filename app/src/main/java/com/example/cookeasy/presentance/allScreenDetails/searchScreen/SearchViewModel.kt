package com.example.cookeasy.presentance.allScreenDetails.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookeasy.data.meals.Meal
import com.example.cookeasy.data.useCase.SearchUseCase
import com.example.cookeasy.presentance.allScreenDetails.mealsListScreen.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel(){

    private val _state = MutableStateFlow<UIState<List<Meal>?>>(UIState.Success(emptyList()))
    val state = _state.asStateFlow()

    fun getSearch(name:String){
        viewModelScope.launch(Dispatchers.Main) {
            searchUseCase.getMealsContainsString(name)
                .flowOn(Dispatchers.IO)
                .catch {
                    _state.value=UIState.Error(it.message.toString())

                }
                .collect{
                    _state.value=UIState.Success(it)
                }
        }
    }

}