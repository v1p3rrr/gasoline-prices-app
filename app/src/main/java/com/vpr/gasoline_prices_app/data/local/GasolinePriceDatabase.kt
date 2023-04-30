package com.vpr.gasoline_prices_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vpr.gasoline_prices_app.data.local.entity.CityEntity
import com.vpr.gasoline_prices_app.data.local.entity.GasolinePriceEntity

@Database(entities = [GasolinePriceEntity::class, CityEntity::class], version = 1)
//@TypeConverters(MyConverter::class)
abstract class GasolinePriceDatabase : RoomDatabase() {

    abstract fun gasolinePriceDao() : GasolinePriceDao

    companion object {
        const val DB_NAME = "gasoline_price_database"
    }
}