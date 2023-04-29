package com.vpr.gasoline_prices_app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vpr.gasoline_prices_app.domain.model.GasolinePrice

@Entity(tableName = "gasoline_price")
data class GasolinePriceEntity(
    @PrimaryKey
    val gasolineType: String,
    val price: Long,
    val currency: String,
    val date: String,
    val city: String
) {
    fun toGasolinePrice(): GasolinePrice {
        return GasolinePrice(
            gasolineType = gasolineType,
            price = price,
            currency = currency,
            date = date,
            city = city
        )
    }
}
