package com.vpr.gasoline_prices_app.ui.cities_screen

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.vpr.gasoline_prices_app.ui.dates_screen.DatesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesScreen(navController: NavController, snackBarHostState: SnackbarHostState) {
    val viewModel: CitiesViewModel = viewModel()
    val citiesState = viewModel.citiesState.value

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        content = { padding ->
            Surface(modifier = Modifier.padding(padding)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {

                    Text(
                        text = "Выберите город",
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(citiesState.citiesList) { city ->
                            Text(
                                text = city,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate("dates")
                                    }
                                    .padding(horizontal = 8.dp, vertical = 8.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (citiesState.isLoading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        })
}