package com.example.cookeasy.data.apiInterface

import com.example.cookeasy.data.meals.Meals
import com.example.cookeasy.data.meals.areaList.AreaList
import com.example.cookeasy.data.meals.categories.Categories
import com.example.cookeasy.data.meals.mealsbyThingList.MealByThingList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    //thambil image  www.themealdb.com/images/ingredients/Lime-Small.png
    // final previe image    www.themealdb.com/images/media/meals/llcbn01574260722.jpg/preview

    @GET("search.php")
    suspend fun searchMeal(
        @Query("s") name : String
    ) : Meals

    @GET("lookup.php")
    suspend fun getMealById(
        @Query("i") id : Int
    ) : Meals

    @GET("categories.php")
    suspend fun getAllCategoriesList(): Categories

    @GET("random.php")
    suspend fun getSingleRandom() : Meals

    @GET("filter.php")
    suspend fun getMealByCategory(
        @Query("c") category : String
    ) : MealByThingList

    @GET("filter.php")
    suspend fun getMealByArea(
        @Query("a") area : String
    ) : MealByThingList

    @GET("list.php?a=list")
    suspend fun getAreaList() : AreaList

}