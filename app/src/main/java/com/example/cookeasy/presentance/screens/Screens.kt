package com.example.cookeasy.presentance.screens

sealed class Screens(val route : String){
    object HomeScreen : Screens(route = "home_screen")
    object WelcomeScreen : Screens(route = "welcome_screen")

    object MealsList : Screens(route = "meals_list_screen")
    object MealDetails : Screens(route = "meal_details_screen")
    object SearchScreen : Screens(route = "fav_search_screen")
}
