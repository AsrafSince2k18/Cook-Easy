package com.example.cookeasy.presentance.allScreenDetails.mealDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookeasy.data.meals.Meal
import com.example.cookeasy.data.useCase.DetailsScreenUSeCase
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
class MealDetailsViewModel @Inject constructor(
    private val detailsScreenUSeCase: DetailsScreenUSeCase
) : ViewModel(){

    private val _mealState = MutableStateFlow<UIState<Meal>>(UIState.Loading)
    val mealState = _mealState.asStateFlow()

    fun getMealId(id : Int){
        viewModelScope.launch (Dispatchers.Main){
            detailsScreenUSeCase.getMealId(id=id).
            flowOn(Dispatchers.IO).
                catch {e->
                    _mealState.value=UIState.Error(e.message.toString())
                }.collect{
                _mealState.value=UIState.Success(data = it)
            }
        }
    }

}