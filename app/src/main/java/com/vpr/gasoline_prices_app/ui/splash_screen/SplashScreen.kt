package com.vpr.gasoline_prices_app.ui.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gasoline_prices_app.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(navController: NavController, snackBarHostState: SnackbarHostState, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        scope.launch {
            delay(3000L) // TODO Initialize
            navController.navigate("cities")
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        content = { padding ->
            Surface(modifier = Modifier.padding(padding).fillMaxSize(),
                color = MaterialTheme.colorScheme.background) {
                Box(modifier = Modifier.padding(all = 50.dp), contentAlignment = Alignment.Center){
                    Image(painter = painterResource(id = R.drawable.ic_lukoil_logo), contentDescription = "Lukoil Icon")
                }

            }
        }
    )
}

@Preview
@Composable
fun PreviewSplashScreen() {

    //val snackBarHostState = remember { SnackbarHostState() }
    //SplashScreen(snackBarHostState, reme)
}