package com.example.cookeasy.presentance.allScreenDetails.searchScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.cookeasy.R
import com.example.cookeasy.data.meals.Meal
import com.example.cookeasy.presentance.allScreenDetails.mealsListScreen.UIState
import com.example.cookeasy.presentance.composable.DetailsLoading
import com.example.cookeasy.presentance.composable.ErrorScreen
import com.example.cookeasy.presentance.composable.ImageLoader
import com.example.cookeasy.presentance.screens.Screens

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val state by searchViewModel.state.collectAsState()

    Scaffold(
        topBar = {
            SearchQuery(navHostController = navHostController)
        }
    )
    {paddingValue->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValue)
            .padding(4.dp)){

            when (state) {
                is UIState.Error -> {
                    ErrorScreen {
                        searchViewModel.getSearch("")
                    }
                }
                UIState.Loading -> {
                    DetailsLoading()
                }
                is UIState.Success -> {
                    val meal = (state as UIState.Success).data
                    meal?.let {
                        LazyColumn {
                            items(meal) {
                                MealListItem(it, onClick = {
                                    navHostController.navigate("${Screens.MealDetails.route}/${it.idMeal.toInt()}")
                                })
                            }
                        }
                    } ?: SearchNotFound()
                }

            }

        }
    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchQuery(
    searchViewModel: SearchViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {


    var query by remember {
        mutableStateOf("")
    }

    val focus = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current

    val keyBoardControl = LocalSoftwareKeyboardController.current


    OutlinedTextField(
        value = query,
        onValueChange = {
            query = it
            searchViewModel.getSearch(it)
        },
        leadingIcon = {
            IconButton(onClick = {
                navHostController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "back"
                )
            }
        },
        placeholder = {
            Text(text = "Search meal")
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                searchViewModel.getSearch(query)
                focusManager.clearFocus()
                keyBoardControl?.hide()
            }
        ),
        trailingIcon = {
                       IconButton(onClick = {
                           if(query.isNotEmpty()){
                               query=""
                           }
                       }) {
                           Icon(imageVector = Icons.Default.Close,
                               contentDescription = "close")
                       }
        }

        ,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .focusRequester(focus)
    )



}

@Composable
fun MealListItem(
    meal: Meal,
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
            meal.let { meal ->
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

@Composable
fun SearchNotFound() {

    val composition = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.search))
    val progress by animateLottieCompositionAsState(composition = composition.value)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition.value,
            progress = {
                progress
            },
            alignment = Alignment.Center,
            modifier = Modifier.size(250.dp)
        )

        androidx.compose.material.Text(
            text = "Enter the correct meal name",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(4.dp))


    }

}