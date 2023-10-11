package com.example.cookeasy.presentance.composable


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cookeasy.R

@Composable
fun ImageLoader(
    modifier : Modifier = Modifier,
    image : String
) {
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest
            .Builder(context)
            .crossfade(true)
            .crossfade(300)
            .data(image)
            .build(),
        error = painterResource(id = R.drawable.place_holder),
        contentDescription = "image",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}