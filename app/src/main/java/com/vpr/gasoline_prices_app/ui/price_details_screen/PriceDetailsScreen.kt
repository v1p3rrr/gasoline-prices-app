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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceDetailsScreen(navController: NavController, snackBarHostState: SnackbarHostState) {
    val viewModel: PriceDetailsViewModel = hiltViewModel()
    val priceDetailsState = viewModel.priceDetailsState.value

    LaunchedEffect(Unit) {

    }

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
                            text = priceDetailsState.gasolinePriceListChosen[0].date
                        )
                        Text(
                            modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                            textAlign = TextAlign.Center,
                            text = priceDetailsState.gasolinePriceListToday[0].date
                        )
                    }
                }
                Row(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(modifier = Modifier.width(120.dp).padding(start = 16.dp), text = "А-92")
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = priceDetailsState.gasolinePriceListChosen.find { it.gasolineType == "92" }?.price?.toString() ?: "—"
                    )
                    val a = priceDetailsState.gasolinePriceListChosen.find { it.gasolineType == "92" }?.price
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = priceDetailsState.gasolinePriceListToday.find { it.gasolineType == "92" }?.price?.toString() ?: "—"
                    )
                }
                Row(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(modifier = Modifier.width(120.dp).padding(start = 16.dp), text = "А-95")
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = priceDetailsState.gasolinePriceListChosen.find { it.gasolineType == "95" }?.price?.toString() ?: "—"
                    )
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = priceDetailsState.gasolinePriceListToday.find { it.gasolineType == "95" }?.price?.toString() ?: "—"
                    )
                }
                Row(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(modifier = Modifier.width(120.dp).padding(start = 16.dp), text = "А-98")
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = priceDetailsState.gasolinePriceListChosen.find { it.gasolineType == "98" }?.price?.toString() ?: "—"
                    )
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = priceDetailsState.gasolinePriceListToday.find { it.gasolineType == "98" }?.price?.toString() ?: "—"
                    )
                }
                Row(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(modifier = Modifier.width(120.dp).padding(start = 16.dp), text = "ДТ")
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = priceDetailsState.gasolinePriceListChosen.find { it.gasolineType == "diesel" }?.price?.toString() ?: "—"
                    )
                    Text(
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        text = priceDetailsState.gasolinePriceListToday.find { it.gasolineType == "diesel" }?.price?.toString() ?: "—"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewPriceDetailsScreen() {
    //val snackBarHostState = remember { SnackbarHostState() }
    //PriceDetailsScreen(snackBarHostState)
}