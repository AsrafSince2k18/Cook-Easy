package com.example.cookeasy.presentance.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.material3.LinearProgressIndicator

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp



@Composable
fun DetailsLoading() {


Spacer(modifier = Modifier.height(4.dp))
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth()
    )

}