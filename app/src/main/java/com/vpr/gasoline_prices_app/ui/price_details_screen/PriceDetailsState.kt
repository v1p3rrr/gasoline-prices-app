package com.vpr.gasoline_prices_app.ui.price_details_screen

import com.vpr.gasoline_prices_app.domain.model.GasolinePrice

data class PriceDetailsState (
    val localGasolinePrice : GasolinePrice? = null,
    val isLoading: Boolean = false
)