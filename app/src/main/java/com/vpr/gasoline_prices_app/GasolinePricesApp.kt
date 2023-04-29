package com.vpr.gasoline_prices_app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GasolinePricesApp : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}