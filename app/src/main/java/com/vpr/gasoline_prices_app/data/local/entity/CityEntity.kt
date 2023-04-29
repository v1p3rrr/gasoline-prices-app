package com.vpr.gasoline_prices_app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String
)