package com.vpr.gasoline_prices_app.data.remote

import com.vpr.gasoline_prices_app.data.remote.dto.CityWithPriceListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GasolinePricesService {

    @GET("./cities")
    suspend fun getCitiesList(): List<String>

    @GET("./price")
    suspend fun getPriceByCity(
        @Query("city") city: String
    ): CityWithPriceListDTO

    @GET("./price")
    suspend fun getPriceByCityAndDate(
        @Query("city") city: String,
        @Query("date") date: String
    ): CityWithPriceListDTO

    @GET("./price")
    suspend fun getPricesListByCityAndDateRange(
        @Query("city") city: String,
        @Query("dateStart") dateStart: String,
        @Query("dateEnd") dateEnd: String
    ): CityWithPriceListDTO
}