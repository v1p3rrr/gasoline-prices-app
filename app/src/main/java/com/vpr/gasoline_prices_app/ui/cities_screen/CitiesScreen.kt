package com.vpr.gasoline_prices_app.ui.cities_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CitiesScreen() {
    val cities = listOf("New York", "London", "Paris", "Sydney", "Tokyo")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){

        Text(
            text = "Города:",
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(cities) { city ->
                Text(
                    text = city,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    CitiesScreen()
}