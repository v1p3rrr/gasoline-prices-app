package com.vpr.gasoline_prices_app.domain.repository

import com.vpr.gasoline_prices_app.domain.model.GasolinePrice
import com.vpr.gasoline_prices_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface GasolinePriceRepository {
    suspend fun getGasolinePriceByCityAndDate(city: String, date: String) : Flow<Resource<List<GasolinePrice>>>

    suspend fun getGasolinePricesListByCityAndDateRange(city: String, dateStart: String, dateEnd: String): Flow<Resource<List<GasolinePrice>>>

    suspend fun getCities() : Flow<Resource<List<String>>>
}