package com.example.cookeasy.presentance.lotileSetUp

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.cookeasy.presentance.lotileSetUp.viewModel.SaveViewModel
import com.example.cookeasy.presentance.screens.Screens
import com.google.accompanist.pager.HorizontalPagerIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    navHostController: NavHostController,
    viewModel : SaveViewModel = hiltViewModel()
) {


    val item = listOf(
        LotileSealed.FirstScreen,
        LotileSealed.SecondScreen,
        LotileSealed.ThirdScreen
    )
    val pagerState = rememberPagerState{3}

    Column(modifier = Modifier.
    fillMaxSize()
        .padding(12.dp)) {
            TextButton(onClick = {
                viewModel.saveOnBoardCases()
                navHostController.popBackStack()
                navHostController.navigate(Screens.WelcomeScreen.route)
            },
                modifier = Modifier.align(Alignment.End)) {
                Text(text = "Skip",
                    fontStyle = MaterialTheme.typography.displayMedium.fontStyle,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black

                )

            }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
                .weight(8f)
        ) { position ->


            Anim(lotileSealed = item[position])
        }

            HorizontalPagerIndicator(pagerState = pagerState,
                pageCount = pagerState.pageCount,
                activeColor = Color.Red,
                modifier = Modifier.weight(1f)
                    .align(Alignment.CenterHorizontally))

            AnimStartButton(pagerState = pagerState,
                onClick = {
                    viewModel.saveOnBoardCases()
                    navHostController.popBackStack()
                    navHostController.navigate(Screens.WelcomeScreen.route)

                },
                modifier = Modifier.fillMaxWidth().weight(1f)
                    .padding(4.dp))


    }

}

@SuppressLint("SuspiciousIndentation")
@Composable
fun Anim(
    lotileSealed: LotileSealed,

) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(lotileSealed.image))
    val progress by animateLottieCompositionAsState(composition = composition)


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxSize(0.7f)
            )

            Text(
                text = lotileSealed.title,
                fontWeight = FontWeight.Bold,
                fontStyle = MaterialTheme.typography.displayLarge.fontStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )

            Text(
                text = lotileSealed.description,
                fontWeight = FontWeight.SemiBold,
                fontStyle = MaterialTheme.typography.displayMedium.fontStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            )

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimStartButton(
    pagerState: PagerState,
    onClick:() -> Unit,
    modifier: Modifier
) {

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center){
        AnimatedVisibility(
            visible = pagerState.currentPage==2) {
            Button(onClick = { onClick() },
                modifier=modifier) {
                Text(text = "Get Started")
            }
        }
    }

}