package com.vpr.gasoline_prices_app.data.local

import androidx.room.*
import com.vpr.gasoline_prices_app.data.local.entity.*

@Dao
interface GasolinePriceDao {

    @Query("SELECT * FROM gasoline_price")
    suspend fun getAllGasolinePrices(): List<GasolinePriceEntity>

    @Query("SELECT * FROM gasoline_price WHERE city = :city")
    suspend fun getGasolinePricesByCity(city: String): List<GasolinePriceEntity> //todo add in repository

    @Query("SELECT * FROM gasoline_price WHERE city = :city AND date = :date LIMIT 1")
    suspend fun getGasolinePriceByCityAndDate(city: String, date: String): GasolinePriceEntity?

    @Query("SELECT * FROM gasoline_price WHERE city = :city AND date(date) BETWEEN date(:dateStart) AND date(:dateEnd)")
    suspend fun getGasolinePricesByCityAndDateRange(city: String, dateStart: String, dateEnd: String): List<GasolinePriceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGasolinePrice(gasolinePriceEntity: GasolinePriceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGasolinePriceList(gasolinePriceEntitiesList: List<GasolinePriceEntity>)

    @Query("DELETE FROM gasoline_price WHERE city = :city AND date = :date")
    suspend fun deleteGasolinePriceByCityAndDate(city: String, date: String)

    @Query("DELETE FROM gasoline_price WHERE city = :city AND date(date) BETWEEN date(:dateStart) AND date(:dateEnd)")
    suspend fun deleteGasolinePricesByCityAndDateRange(city: String, dateStart: String, dateEnd: String)


    @Update
    suspend fun updateGasolinePrice(gasolinePrice: GasolinePriceEntity)

    @Delete
    suspend fun deleteGasolinePriceByEntity(gasolinePrice: GasolinePriceEntity)



    @Query("SELECT * FROM city")
    suspend fun getAllCities(): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<CityEntity>)

    @Query("DELETE FROM city")
    suspend fun deleteAllCities()
    @Query("DELETE FROM city WHERE name = :name")
    suspend fun deleteCityByName(name: String)

    @Update
    suspend fun updateCity(city: CityEntity)
}