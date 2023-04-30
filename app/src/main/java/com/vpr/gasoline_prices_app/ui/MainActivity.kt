package com.vpr.gasoline_prices_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vpr.gasoline_prices_app.ui.cities_screen.CitiesScreen
import com.vpr.gasoline_prices_app.ui.dates_screen.DatesScreen
import com.vpr.gasoline_prices_app.ui.price_details_screen.PriceDetailsScreen
import com.vpr.gasoline_prices_app.ui.splash_screen.SplashScreen
import com.vpr.gasoline_prices_app.ui.theme.GasolinepricesappTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GasolinepricesappTheme() {
                Navigation()
                GasolinepricesappTheme {
                    Surface {
                        Navigation()
                    }
                }
            }
        }
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun Navigation() {
        val snackBarHostState = remember { SnackbarHostState() }
        val navController = rememberNavController()
        val bundle = Bundle()
        bundle.putString("key", "value")

        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") { SplashScreen(navController, snackBarHostState) }
            composable("cities") {
                CitiesScreen(navController, snackBarHostState)}
            composable("dates") { DatesScreen(navController, snackBarHostState) }
            composable("prices") { PriceDetailsScreen(navController, snackBarHostState) }
        }
    }
}