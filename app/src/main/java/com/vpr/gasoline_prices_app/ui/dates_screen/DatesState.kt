package com.vpr.gasoline_prices_app.ui.dates_screen

import com.vpr.gasoline_prices_app.domain.model.GasolinePrice

data class DatesState (
    val gasolinePriceList: List<GasolinePrice> = emptyList(),
    val isLoading: Boolean = false,
    val dateStart: String = "01.04.2023", //todo datetime?
    val dateEnd: String = "01.03.2023"
)
