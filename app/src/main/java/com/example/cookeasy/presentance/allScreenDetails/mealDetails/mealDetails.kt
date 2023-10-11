package com.example.cookeasy.presentance.allScreenDetails.mealDetails


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cookeasy.data.meals.Meal
import com.example.cookeasy.presentance.allScreenDetails.mealsListScreen.UIState
import com.example.cookeasy.presentance.composable.DetailsLoading
import com.example.cookeasy.presentance.composable.ErrorScreen

@Composable
fun MealDetails(
    navHostController: NavHostController,
    mealID : Int?=null,
    mealDetailsViewModel: MealDetailsViewModel= hiltViewModel()
){

    val context = LocalContext.current

    val state by mealDetailsViewModel.mealState.collectAsState()


    LaunchedEffect(key1 = Unit){
        mealID?.let {
            mealDetailsViewModel.getMealId(it)
        }
    }


    when(state){
        is UIState.Error -> {
            ErrorScreen {
                mealID?.let {
                    mealDetailsViewModel.getMealId(it)
                }
            }
        }
        UIState.Loading -> {
            DetailsLoading()
        }
        is UIState.Success -> {
            DetailsToolBar(
                meal = (state as UIState.Success<Meal>).data,
                navHostController =navHostController,
                context =context
            )
        }
    }


}