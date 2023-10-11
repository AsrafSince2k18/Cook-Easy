package com.example.cookeasy.presentance.allScreenDetails.mealDetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.cookeasy.data.meals.Meal
import com.example.cookeasy.presentance.composable.ImageLoader
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import kotlin.reflect.full.memberProperties

@Composable
fun DetailsToolBar(
    meal: Meal,
    navHostController: NavHostController,
    context: Context
) {

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxWidth(),
        state = rememberCollapsingToolbarScaffoldState(),
        scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        toolbar = {
            ToolBarContent(
                meal = meal,
                navHostController = navHostController
            )
        }
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Outlined.Fastfood,
                        contentDescription = "food"
                    )
                    Text(
                        text = meal.strCategory,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Row {
                    Icon(
                        imageVector = Icons.Outlined.Flag,
                        contentDescription = "food"
                    )
                    Text(
                        text = meal.strArea,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                IconButton(onClick = {
                    if (meal.strYoutube.isNotEmpty()) {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(meal.strYoutube)
                            )
                        )
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.SmartDisplay,
                        contentDescription = "youtubeIcon",
                        tint = Color.Red
                    )
                }

            }

            //image food
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Text(
                    text = meal.strMeal,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Ingredients",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(4.dp))

                IngredientsScopeContent(meal = meal)

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Instructions",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )

                Text(
                    text = meal.strInstructions,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))
                YouTubeScreen(url = meal.strYoutube)

                Spacer(modifier = Modifier.height(4.dp))

            }
        }
    }
}


@Composable
fun IngredientsScopeContent(meal: Meal) {

    for (indexForLoop in 1..20 step 4) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(4) { repeatIndex ->
                val ingredients = Meal::class.memberProperties.find {
                    it.name == "strIngredient${indexForLoop + repeatIndex}"
                }?.get(meal) as? String

                val measure = Meal::class.memberProperties.find {
                    it.name == "strMeasure${indexForLoop + repeatIndex}"
                }?.get(meal) as? String

                if (ingredients.isNullOrBlank()) return

                Card(
                    modifier = Modifier
                        .width(80.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = CenterHorizontally
                    ) {
                        ImageLoader(
                            image = "https://www.themealdb.com/images/ingredients/$ingredients.png",
                            modifier = Modifier.size(80.dp)
                        )

                        Text(
                            text = "$measure $ingredients",
                            modifier = Modifier
                                .padding(4.dp)
                                .align(CenterHorizontally),
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }


        }
    }


}

@Composable
fun ToolBarContent(
    meal: Meal,
    navHostController: NavHostController
) {

    ImageLoader(
        image = meal.strMealThumb,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        IconButton(onClick = {
            navHostController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "left",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background, shape = CircleShape)
            )
        }
    }

}

@Composable
fun YouTubeScreen(url: String) {
    val videoId = url.substring(url.indexOf("v=") + 2)
    AndroidView(
        factory = {
            val view = YouTubePlayerView(it)
            view.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.cueVideo(videoId, 0f)
                    }
                }
            )
            view
        })
}

