package com.vpr.gasoline_prices_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.vpr.gasoline_prices_app.ui.cities_screen.CitiesScreen
import com.vpr.gasoline_prices_app.ui.splash_screen.SplashScreen
import com.vpr.gasoline_prices_app.ui.theme.GasolinepricesappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                    Navigation()
        }
    }
}


@Composable
fun Navigation() {
    val snackBarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController, snackBarHostState) }
        composable("cities") { CitiesScreen(navController, snackBarHostState) }
    }
}