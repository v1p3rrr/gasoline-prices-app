package com.vpr.gasoline_prices_app.data.remote.dto

import com.vpr.gasoline_prices_app.data.local.entity.GasolinePriceEntity
import com.vpr.gasoline_prices_app.domain.model.GasolinePrice

data class CityWithPriceListDTO(
    val city: String,
    val pricesWithDateDTO: List<PriceWithDateDTO>
) {
    /**
     * Mapper to database entity.
     * @return database entity [GasolinePriceEntity]
     */
    fun toGasolinePriceEntitiesList(): List<GasolinePriceEntity> {
        return pricesWithDateDTO.map {
            GasolinePriceEntity(
                gasolineType = it.gasolineType,
                price = it.price,
                currency = it.currency,
                date = it.date,
                city = this.city
            )
        }
    }

    /**
     * Mapper to domain entity. Better to save object to database first using [toGasolinePriceEntitiesList], then use [GasolinePriceEntity.toGasolinePrice] on the object retrieved from database.
     * @return domain model [GasolinePrice]
     */
    fun toGasolinePrice(): List<GasolinePrice> {
        return pricesWithDateDTO.map {
            GasolinePrice(
                gasolineType = it.gasolineType,
                price = it.price,
                currency = it.currency,
                date = it.date,
                city = this.city
            )
        }
    }
}