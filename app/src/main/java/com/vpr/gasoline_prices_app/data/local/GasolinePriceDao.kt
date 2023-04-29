package com.vpr.gasoline_prices_app.data.local

import androidx.room.*
import com.vpr.gasoline_prices_app.data.local.entity.*

@Dao
interface GasolinePriceDao {

    @Query("SELECT * FROM gasoline_price")
    suspend fun getAllGasolinePrices(): List<GasolinePriceEntity>

    @Query("SELECT * FROM gasoline_price WHERE city = :city")
    suspend fun getGasolinePricesByCity(city: String): List<GasolinePriceEntity>

    @Query("SELECT * FROM gasoline_price WHERE city = :city AND date = :date LIMIT 1")
    suspend fun getGasolinePriceByCityAndDate(city: String, date: String): GasolinePriceEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGasolinePrice(gasolinePriceEntity: GasolinePriceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGasolinePriceList(gasolinePriceEntitiesList: List<GasolinePriceEntity>)

    @Query("DELETE FROM gasoline_price WHERE city = :city AND date = :date")
    suspend fun deleteGasolinePriceByCityAndDate(city: String, date: String)

    @Update
    suspend fun update(gasolinePrice: GasolinePriceEntity)

    @Delete
    suspend fun delete(gasolinePrice: GasolinePriceEntity)
}