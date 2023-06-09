package com.vpr.gasoline_prices_app.ui.cities_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vpr.gasoline_prices_app.ui.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesScreen(navController: NavController, snackBarHostState: SnackbarHostState) {
    val viewModel: CitiesViewModel = hiltViewModel()
    val citiesState = viewModel.citiesState.value
    LaunchedEffect(Unit) {
        viewModel.getCities()
    }
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
                                        navController.navigate(
                                            Screen.DatesScreen.withArgs(
                                                city
                                            )
                                        ); Log.d("MyTag", city)
                                    }
                                    .padding(horizontal = 8.dp, vertical = 8.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        })
}