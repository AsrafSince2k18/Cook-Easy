package com.example.cookeasy.presentance.allScreenDetails.mealsListScreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookeasy.data.meals.mealsbyThingList.MealByThing
import com.example.cookeasy.data.useCase.MealByThingCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MealListViewModel @Inject constructor(
    private val mealByThingCase: MealByThingCase
) : ViewModel(){

  private val _state = MutableStateFlow<UIState<List<MealByThing>>>(UIState.Loading)

    val state = _state.asStateFlow()



    fun getMealCategory(category : String){
        viewModelScope.launch(Dispatchers.Main) {
           _state.value=UIState.Loading
            mealByThingCase.getMealByCategory(category=category)
                .flowOn(Dispatchers.IO).catch {
                    _state.value = UIState.Error(message = it.message.toString())
                }.collect{meal->
                    _state.value=UIState.Success(data = meal)
                }
        }
    }


    fun getMealArea(area : String){
        viewModelScope.launch(Dispatchers.Main) {
            _state.value=UIState.Loading
            mealByThingCase.getAreaMeal(area= area)
                .flowOn(Dispatchers.IO).catch {
                    _state.value = UIState.Error(message = it.message.toString())
                }.collect{meal->
                    _state.value=UIState.Success(data = meal)
                }
        }
    }

}