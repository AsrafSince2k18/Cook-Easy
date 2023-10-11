package com.example.cookeasy.presentance.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.cookeasy.R

@Composable
fun ErrorScreen(
    showRefreshButton: Boolean =true,
    onRetry: () -> Unit,

) {


    val error = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error))
    val progress  by animateLottieCompositionAsState(composition = error.value)


    Column(
        modifier=Modifier
            .fillMaxSize()
        , verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {
            LottieAnimation(
                composition =error.value,
                progress = {
                    progress
                },
                alignment = Alignment.Center,
                modifier = Modifier.
                size(250.dp)
            )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Check your internet connection",
            style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedButton(onClick = {
            if(showRefreshButton) onRetry() }) {
            Text(text = "Retry")
        }

    }

}