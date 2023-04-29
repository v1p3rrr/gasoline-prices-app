package com.vpr.gasoline_prices_app.domain.model

data class GasolinePrice(
    val gasolineType: String,
    val price: Long,
    val currency: String,
    val date: String,
    val city: String
)
