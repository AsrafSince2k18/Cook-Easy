package com.example.cookeasy.data.useCase

import com.example.cookeasy.data.repository.Repository

class MealByThingCase(private val repository: Repository) {

     fun getMealByCategory(category :String) = repository.getMealByCategory(category = category)

    fun getAreaMeal(area : String)= repository.getMealsByArea(area=area)
}