package com.example.cookeasy.presentance.allScreenDetails.mealsListScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cookeasy.data.meals.mealsbyThingList.MealByThing
import com.example.cookeasy.presentance.composable.ImageLoader
import com.example.cookeasy.presentance.composable.ErrorScreen
import com.example.cookeasy.presentance.composable.ShimmerListError
import com.example.cookeasy.presentance.screens.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsListScreen(
    navHostController: NavHostController,
    from: String?,
    thing: String?,
    mealListViewModel: MealListViewModel = hiltViewModel()
) {

    val state by mealListViewModel.state.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = thing ?: "")
            },
                navigationIcon = {
                    IconButton(onClick = {navHostController.popBackStack()}) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "back")
                    }
                }
            )
            when (from) {
                "category" -> mealListViewModel.getMealCategory(thing!!)
                "area" -> mealListViewModel.getMealArea(thing!!)
            }
        }
    )
    {paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)){
            when(state){
                is UIState.Error -> {
                    ErrorScreen{
                        when(from){
                            "category" -> mealListViewModel.getMealCategory(thing!!)
                            "area" -> mealListViewModel.getMealArea(thing!!)
                        }
                    }
                }
                UIState.Loading -> {
                    ShimmerListError()
                }
                is UIState.Success -> {
                    val meal = (state as UIState.Success).data
                    ListShowMeal(meal = meal,
                        navHostController = navHostController)
                }
            }
        }
    }






}


@Composable
fun ListShowMeal(meal : List<MealByThing>,navHostController: NavHostController) {

    LazyColumn{
        items(meal){
            MealListItem(mealData = it) {
                navHostController.navigate(
                    "${Screens.MealDetails.route}/${it.idMeal.toInt()}"
                )
            }
        }
    }

}


@Composable
fun MealListItem(
    mealData: MealByThing,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .padding(8.dp), colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )

    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
        ) {
            mealData.let { meal ->
                ImageLoader(
                    image = meal.strMealThumb,
                    modifier = Modifier
                        .aspectRatio(16 / 9f)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            onClick()
                        })

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = meal.strMeal,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
    }
}