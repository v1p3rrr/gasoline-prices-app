package com.vpr.gasoline_prices_app.domain.repository

import com.vpr.gasoline_prices_app.domain.model.GasolinePrice
import com.vpr.gasoline_prices_app.util.Resource
import kotlinx.coroutines.flow.Flow


interface GasolinePriceRepository {
    fun getGasolinePricesByCityAndDate(city: String, date: String) : Flow<Resource<GasolinePrice>>
    fun getCities() : Flow<Resource<List<String>>>
}