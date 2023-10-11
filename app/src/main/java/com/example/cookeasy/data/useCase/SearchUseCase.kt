package com.example.cookeasy.data.useCase

import com.example.cookeasy.data.repository.Repository

class SearchUseCase (private val repository: Repository) {

    fun getMealsContainsString(name : String)=repository.getMealsContainsString(mealName=name)

}