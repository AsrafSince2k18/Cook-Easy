package com.example.cookeasy.data.domain

import android.util.Log
import com.example.cookeasy.data.apiInterface.ApiServices
import com.example.cookeasy.data.repository.Repository
import kotlinx.coroutines.flow.flow

class RepositoryImpl(
    private val apiServices: ApiServices
) : Repository {

    //Random image generate
    override fun getRandomMeal() = flow {
        emit(apiServices.getSingleRandom().meals.first())
    }

    //specific category
    override fun getCategoryResponse() = flow {
        try {
            val network = apiServices.getAllCategoriesList().categories
            emit(network)
        } catch (e: Exception) {
            Log.e("myTag", e.message.toString())
        }
    }

    //specific country list show
    override fun getAreasList() = flow {
        try {
            val area = apiServices.getAreaList().meals
            emit(area)
        } catch (e: Exception) {
            Log.e("myTag", e.message.toString())
        }
    }

    // specific meal list show
    override fun getMealDetails(id: Int) = flow {
        emit(apiServices.getMealById(id = id).meals.first())
    }


    // search meal
    override fun getMealsContainsString(mealName: String) = flow {
        emit(apiServices.searchMeal(name = mealName).meals)
    }


    //specific category meals
    override fun getMealByCategory(category: String) = flow {
        emit(apiServices.getMealByCategory(category).meals)
    }


    //specific meal country
    override fun getMealsByArea(area: String) = flow {
        emit(apiServices.getMealByArea(area).meals)

    }

}