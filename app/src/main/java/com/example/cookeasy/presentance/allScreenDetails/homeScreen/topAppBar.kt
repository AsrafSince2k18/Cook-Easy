package com.example.cookeasy.presentance.allScreenDetails.homeScreen

import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    onSearchClick :() ->Unit
) {

    TopAppBar(title = {
        Text(text = "Easy to cook",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold)
            },
        actions = {
                IconButton(onClick = { onSearchClick() }) {
                    Icon(imageVector = Icons.Outlined.Search,
                        contentDescription = "searchFood")
                }
            }
        )

}