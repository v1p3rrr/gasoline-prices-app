package com.vpr.gasoline_prices_app.ui.price_details_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceDetailsScreen(snackBarHostState: SnackbarHostState) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        content = { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            Column() {
                Row(Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
                    Text(modifier = Modifier.width(120.dp).padding(start = 16.dp), text = "")
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = "15.07.2021"
                    )
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = "15.12.2021"
                    )
                }
                Row(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(modifier = Modifier.width(120.dp).padding(start = 16.dp), text = "А-92")
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = "10.00 руб."
                    )
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = "10.50 руб."
                    )
                }
                Row(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(modifier = Modifier.width(120.dp).padding(start = 16.dp), text = "А-95")
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = "11.00 руб."
                    )
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = "11.50 руб."
                    )
                }
                Row(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(modifier = Modifier.width(120.dp).padding(start = 16.dp), text = "А-98")
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = "10.00 руб."
                    )
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = "10.50 руб."
                    )
                }
                Row(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(modifier = Modifier.width(120.dp).padding(start = 16.dp), text = "А-100")
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = "10.00 руб."
                    )
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = "10.50 руб."
                    )
                }
                Row(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(modifier = Modifier.width(120.dp).padding(start = 16.dp), text = "ДТ")
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = "10.00 руб."
                    )
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = "10.50 руб."
                    )
                }
            }
        }
    })
}

@Preview
@Composable
fun PreviewPriceDetailsScreen() {
    val snackBarHostState = remember { SnackbarHostState() }
    PriceDetailsScreen(snackBarHostState)
}