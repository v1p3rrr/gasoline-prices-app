package com.vpr.gasoline_prices_app.ui.price_details_screen

import com.vpr.gasoline_prices_app.domain.model.GasolinePrice

data class PriceDetailsState (
    val gasolinePriceListChosen : List<GasolinePrice> = emptyList(),
    val gasolinePriceListToday : List<GasolinePrice> = emptyList(),
    val isLoading: Boolean = false
)