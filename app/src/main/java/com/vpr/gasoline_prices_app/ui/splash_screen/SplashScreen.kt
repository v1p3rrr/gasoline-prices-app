package com.vpr.gasoline_prices_app.ui.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gasoline_prices_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(snackbarHostState: SnackbarHostState, modifier: Modifier = Modifier) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
    val snackbarHostState = remember { SnackbarHostState() }
    SplashScreen(snackbarHostState)
}