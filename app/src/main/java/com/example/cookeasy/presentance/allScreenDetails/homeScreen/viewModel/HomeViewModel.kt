package com.example.cookeasy.presentance.allScreenDetails.homeScreen.viewModel

import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookeasy.data.useCase.CategoriesAndAreaCase
import com.example.cookeasy.presentance.allScreenDetails.homeScreen.state.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoriesAndAreaCase: CategoriesAndAreaCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeScreenState())
    val homeState = _homeState.asStateFlow()


    init {
        getCategory()
        getAreaList()
    }

    fun getRandomMeal() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                categoriesAndAreaCase.getRandom().collect { meal ->
                    _homeState.update {
                        it.copy(mealDetails = meal)
                    }
                }
            } catch (e: Exception) {
                Log.e("myTag", e.toString())
            }
        }
    }

    private fun getCategory() {
        viewModelScope.launch(Dispatchers.Main) {
            _homeState.update {
                it.copy(isLoading = true)
            }
            categoriesAndAreaCase.categoryResponse().flowOn(Dispatchers.IO)
                .collect { category ->
                    _homeState.update {
                        it.copy(categoryResponse = category, isLoading = false)
                    }
                }
        }
    }


    private fun getAreaList() {
        viewModelScope.launch(Dispatchers.Main) {
            _homeState.update {
                it.copy(isLoading = true)
            }
            categoriesAndAreaCase.getAreaList()
                .flowOn(Dispatchers.IO).collect { area ->
                    _homeState.update {
                        it.copy(areaResponse = area, isLoading = false)
                    }
                }
        }

    }


}