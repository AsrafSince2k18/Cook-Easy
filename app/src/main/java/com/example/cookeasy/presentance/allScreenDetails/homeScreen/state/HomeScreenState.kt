package com.example.cookeasy.presentance.allScreenDetails.homeScreen.state

import com.example.cookeasy.data.meals.Meal
import com.example.cookeasy.data.meals.areaList.Area
import com.example.cookeasy.data.meals.categories.Category

data class HomeScreenState(
    val isLoading : Boolean = false,
    val mealDetails : Meal?=null,
    val categoryResponse : List<Category> = emptyList(),
    val areaResponse : List<Area> = emptyList(),
)
