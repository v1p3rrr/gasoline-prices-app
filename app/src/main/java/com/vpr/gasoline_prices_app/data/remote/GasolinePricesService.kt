package com.vpr.gasoline_prices_app.data.remote

import com.vpr.gasoline_prices_app.data.remote.dto.CityWithPriceListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GasolinePricesService {

    @GET("./cities")
    suspend fun getCitiesList(): List<String>

    @GET("./price/{city}")
    suspend fun getPriceByCity(
        @Path("city") city: String
    ): CityWithPriceListDTO

    @GET("./price/{city}/date/{date}")
    suspend fun getPriceByCityAndDate(
        @Path("city") city: String,
        @Path("date") date: String
    ): CityWithPriceListDTO

    @GET("./price/{city}/date-range/{dateStart}/{dateEnd}")
    suspend fun getPricesListByCityAndDateRange(
        @Path("city") city: String,
        @Path("dateStart") dateStart: String,
        @Path("dateEnd") dateEnd: String
    ): CityWithPriceListDTO
}