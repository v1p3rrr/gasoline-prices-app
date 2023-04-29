package com.vpr.gasoline_prices_app.data.remote.dto

data class PriceWithDateDTO(
    val gasolineType: String,
    val price: Long,
    val currency: String,
    val date: String
)

fun a(){
    val x = listOf<PriceWithDateDTO>()
    x.any{ it.gasolineType == "92" }
}
