package com.example.cookeasy.data.repository

import com.example.cookeasy.data.meals.Meal
import com.example.cookeasy.data.meals.areaList.Area
import com.example.cookeasy.data.meals.categories.Category
import com.example.cookeasy.data.meals.mealsbyThingList.MealByThing
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getCategoryResponse(): Flow<List<Category>>

    fun getMealDetails(id: Int) : Flow<Meal>

    fun getMealsContainsString(mealName : String) : Flow<List<Meal>>

    fun getRandomMeal() : Flow<Meal>

    fun getMealByCategory(category : String) : Flow<List<MealByThing>>

    fun getAreasList() : Flow<List<Area>>

    fun getMealsByArea(area : String) : Flow<List<MealByThing>>
}