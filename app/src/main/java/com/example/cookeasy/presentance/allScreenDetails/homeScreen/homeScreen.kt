package com.example.cookeasy.presentance.allScreenDetails.homeScreen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cookeasy.presentance.composable.ImageLoader
import com.example.cookeasy.presentance.composable.LoaderPageHomeScreen
import com.example.cookeasy.presentance.allScreenDetails.homeScreen.state.HomeScreenState
import com.example.cookeasy.presentance.allScreenDetails.homeScreen.viewModel.HomeViewModel
import com.example.cookeasy.presentance.screens.Screens
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {


    val context = LocalContext.current
    val state by homeViewModel.homeState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val job = remember {
        mutableStateOf<Job?>(null)
    }

    DisposableEffect(key1 = context){
         job.value = coroutineScope.launch {
             while (isActive) {
                 homeViewModel.getRandomMeal()
                 delay(5000)
             }
         }
         onDispose {
             job.value?.cancel()
         }
     }

    /*LaunchedEffect(key1 = state.mealDetails) {
        coroutineScope.launch {
            delay(5000)
            homeViewModel.getRandomMeal()
        }
    }*/

    Scaffold(
        topBar = {
            HomeScreenTopBar(onSearchClick = {
                navHostController.navigate(Screens.SearchScreen.route) })
        }
    ) { paddingValues ->

            Column(
              modifier = Modifier
                  .padding(4.dp)
                  .padding(paddingValues)
            ) {
                ShowScreen(
                    state =state,
                    navHostController =navHostController,
                    context =context
                )

            }

    }
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
private fun ShowHome(
    navHostController: NavHostController,
    state: HomeScreenState,
    context: Context
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        RandomFoodImage(
            navHostController = navHostController,
            state = state,
            context = context
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Categories",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(4.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        CategoryRow(
            state = state,
            navHostController = navHostController
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Country",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(4.dp)
        )

        GridArea(
            homeScreenState = state,
            navHostController = navHostController
        )
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun ShowScreen(
    state : HomeScreenState,
    navHostController: NavHostController,
    context: Context
) {

    Box(modifier = Modifier.fillMaxSize()){
        if(state.isLoading){
            LoaderPageHomeScreen()
        }else{
            ShowHome(
                navHostController =navHostController,
                state =state,
                context =context
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun RandomFoodImage(
    navHostController: NavHostController,
    state: HomeScreenState,
    context: Context
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
            if (isConnected(context = context).not()) {
                Text(text = "error")
            } else {
                state.mealDetails?.let { meal ->

                    ImageLoader(
                        image = meal.strMealThumb,
                        modifier = Modifier
                            .aspectRatio(16 / 9f)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                navHostController.navigate("${Screens.MealDetails.route}/${meal.idMeal.toInt()}")
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

}


@Composable
fun CategoryRow(state: HomeScreenState, navHostController: NavHostController) {
    LazyRow(
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(state.categoryResponse) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                ImageLoader(
                    image = it.strCategoryThumb,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            navHostController.navigate(
                                "${Screens.MealsList.route}/category/${it.strCategory}"
                            )
                        }
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(text = it.strCategory)
            }
        }
    }
}

@Composable
fun GridArea(
    homeScreenState: HomeScreenState,
    navHostController: NavHostController
) {
    LazyHorizontalStaggeredGrid(
        rows =StaggeredGridCells.Fixed(2),
        horizontalItemSpacing = 4.dp,
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight(0.6f)


    ){
        items(homeScreenState.areaResponse){area->
            OutlinedButton(
                onClick = {
                    navHostController.navigate(
                        "${Screens.MealsList.route}/area/${area.strArea}"
                    )
                },
                modifier = Modifier
                    .padding(6.dp)
                    .width(125.dp)
            ) {
                Text(text = area.strArea)
            }
        }
    }

}


//random image wifi or internet doest connect throw error
@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun isConnected(context: Context): Boolean {

    val connectiveManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val network = connectiveManager.activeNetwork ?: return false
    val activeNetwork = connectiveManager.getNetworkCapabilities(network) ?: return false

    return when {
        activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_SUPL) -> true
        activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_MMS) -> true
        activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) -> true
        activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_WIFI_P2P) -> true
        else -> false
    }

}


