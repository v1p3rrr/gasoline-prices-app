package com.vpr.gasoline_prices_app.ui.splash_screen

import androidx.lifecycle.ViewModel
import com.vpr.gasoline_prices_app.domain.repository.GasolinePriceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: GasolinePriceRepository
) : ViewModel() {


}