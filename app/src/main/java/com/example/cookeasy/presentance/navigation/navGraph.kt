package com.example.cookeasy.presentance.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cookeasy.presentance.allScreenDetails.homeScreen.HomeScreen
import com.example.cookeasy.presentance.allScreenDetails.mealDetails.MealDetails
import com.example.cookeasy.presentance.lotileSetUp.WelcomeScreen
import com.example.cookeasy.presentance.allScreenDetails.mealsListScreen.MealsListScreen
import com.example.cookeasy.presentance.allScreenDetails.searchScreen.SearchScreen
import com.example.cookeasy.presentance.screens.Screens

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun NavGraph(navHostController: NavHostController,
             startDestination : String) {

    NavHost(navController = navHostController,
        startDestination = startDestination){

        composable(route=Screens.WelcomeScreen.route){
            WelcomeScreen(navHostController=navHostController)
        }

        composable(route=Screens.HomeScreen.route){
            HomeScreen(navHostController=navHostController)
        }

        composable(route = "${Screens.MealsList.route}/{from}/{thing}",
            arguments = listOf(
                navArgument(name = "from"){
                    type= NavType.StringType
                },
                navArgument(name = "thing"){
                    type= NavType.StringType
                }
            ),

        ){
            val from = it.arguments?.getString("from")
            val thing = it.arguments?.getString("thing")
            MealsListScreen(
                navHostController = navHostController,
                from = from,
                thing = thing
            )
        }

        composable(route = "${Screens.MealDetails.route}/{meal_id}",
            arguments= listOf(
                navArgument(name = "meal_id"){
                    type= NavType.IntType
                }
            )
        ){
            val mealId = it.arguments?.getInt("meal_id")
            MealDetails(navHostController = navHostController, mealID = mealId)
        }

        composable(route= Screens.SearchScreen.route,

        ){
           // val query = it.arguments?.getString("from")
            SearchScreen(navHostController=navHostController)
        }
    }

}

