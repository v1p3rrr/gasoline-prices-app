package com.vpr.gasoline_prices_app.ui.cities_screen

data class CitiesState (
    val citiesList : List<String> = emptyList(),
    val isLoading: Boolean = false
)
