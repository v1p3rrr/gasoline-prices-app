package com.vpr.gasoline_prices_app.ui.dates_screen

import android.annotation.SuppressLint
import android.icu.text.DateFormatSymbols
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

import androidx.navigation.NavController
import com.example.gasoline_prices_app.R
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun DatesScreen(
    navController: NavController,
    snackBarHostState: SnackbarHostState
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    fun createYearList(): List<String> {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val years = mutableListOf<String>()
        for (i in 0..10) {
            years.add((currentYear - 10 + i).toString())
        }
        return years
    }

    val coroutineScope = rememberCoroutineScope()
    val selectedMonth = remember { mutableStateOf("") }
    var selectedYear by remember { mutableStateOf("") }
    val yearList = createYearList()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            coroutineScope.launch {
                                if (!bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                    bottomSheetScaffoldState.bottomSheetState.collapse()
                                }
                            }
                        })
                    }
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    LazyRow(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        itemsIndexed(yearList) { index, year ->
                            Text(
                                text = year,
                                fontSize = 16.sp,
                                color = if (selectedYear == year) Color.Red else Color.Black,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        selectedYear = year
                                    }
                            )
                        }
                    }

                    AndroidView({ CalendarView(it) },
                        Modifier.wrapContentSize(),
                        update = { view ->
                            val today = Calendar.getInstance()
                            today.add(Calendar.MONTH, 1)
                            today.set(Calendar.DAY_OF_MONTH, 1)
                            today.add(Calendar.DATE, -1)

                            val maxDate = today.timeInMillis

                            view.maxDate = maxDate

                            view.setOnDateChangeListener { _, year, month, day ->
                                val calendar = Calendar.getInstance().apply {
                                    set(Calendar.YEAR, year)
                                    set(Calendar.MONTH, month)
                                    set(Calendar.DAY_OF_MONTH, day)
                                }
                                val monthName = SimpleDateFormat("LLLL", Locale.getDefault()).format(calendar.time)
                                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

                                selectedMonth.value  = monthName

                                selectedYear = year.toString()

                            }

                            if (selectedYear.isNotEmpty()) {
                                val yearInt = selectedYear.toInt()
                                view.date = Calendar.getInstance().apply { set(yearInt, 0, 1) }.timeInMillis
                            }
                        }
                    )

                    // Кнопка "Выбрать дату"
                    Button(
                        onClick = {
                            // Обработка нажатия на кнопку
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Подтвердить выбор даты")
                    }
                }
            }
        },
        sheetPeekHeight = 0.dp
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable(onClick = {
                                coroutineScope.launch {
                                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                        bottomSheetScaffoldState.bottomSheetState.expand()
                                    } else {
                                        bottomSheetScaffoldState.bottomSheetState.collapse()
                                    }
                                }
                            })
                            .align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = "12.12.2005"
                    )
                }

                Column() {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable(onClick = {
                                coroutineScope.launch {
                                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                        bottomSheetScaffoldState.bottomSheetState.expand()
                                    } else {
                                        bottomSheetScaffoldState.bottomSheetState.collapse()
                                    }
                                }
                            })
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "25.05.2023"
                    )
                }
            }
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
            ) {
                items(30) { index ->
                    Text(
                        text = "Дата $index",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                navController.navigate("prices")
                            }
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}






@Preview
@Composable
fun PreviewPricesScreen() {
}