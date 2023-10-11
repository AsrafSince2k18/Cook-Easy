package com.example.cookeasy

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.cookeasy.presentance.lotileSetUp.viewModel.WelcomeViewModel
import com.example.cookeasy.presentance.navigation.NavGraph
import com.example.cookeasy.ui.theme.CookEasyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WelcomeViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                Log.d("myTag", viewModel.splashCondition.toString())
                viewModel.splashCondition
            }
        }
        setContent {
            CookEasyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navHostController = rememberNavController()
                    val startDestination = viewModel.destination
                    NavGraph(navHostController = navHostController,
                        startDestination=startDestination)
                }
            }
        }
    }
}

