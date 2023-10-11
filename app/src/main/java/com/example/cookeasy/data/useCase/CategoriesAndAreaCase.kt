package com.example.cookeasy.data.useCase

import com.example.cookeasy.data.repository.Repository

class CategoriesAndAreaCase(private val repository: Repository) {

    fun getRandom() = repository.getRandomMeal()

    fun categoryResponse() = repository.getCategoryResponse()

    fun getAreaList() = repository.getAreasList()


}