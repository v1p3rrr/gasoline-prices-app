package com.vpr.gasoline_prices_app.ui.price_details_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpr.gasoline_prices_app.domain.repository.GasolinePriceRepository
import com.vpr.gasoline_prices_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class PriceDetailsViewModel @Inject constructor(
    private val repository: GasolinePriceRepository
) : ViewModel() {

    private val _priceDetailsState = mutableStateOf(PriceDetailsState())
    val priceDetailsState: State<PriceDetailsState> = _priceDetailsState

    private val _errorMessageSharedFlow = MutableSharedFlow<String>()
    val errorMessageSharedFlow = _errorMessageSharedFlow.asSharedFlow()

    fun getGasolinePriceByCityAndDateChosen(city: String, dateChosen: String) =
        viewModelScope.launch {
            repository.getGasolinePriceByCityAndDate(city = city, date = dateChosen)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _priceDetailsState.value = _priceDetailsState.value.copy(
                                gasolinePriceListChosen = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _errorMessageSharedFlow.emit(result.message ?: "Success")

                        }

                        is Resource.Error -> {
                            _priceDetailsState.value = _priceDetailsState.value.copy(
                                gasolinePriceListChosen = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _errorMessageSharedFlow.emit(result.message ?: "Unknown error")
                        }

                        is Resource.Loading -> {
                            _priceDetailsState.value = _priceDetailsState.value.copy(
                                gasolinePriceListChosen = result.data ?: emptyList(),
                                isLoading = true
                            )
                            _errorMessageSharedFlow.emit(result.message ?: "Loading")
                        }
                    }
                }.launchIn(this)
        }


    fun getGasolinePriceByCityAndDateToday(city: String, dateToday: String) =
        viewModelScope.launch {
            repository.getGasolinePriceByCityAndDate(city = city, date = dateToday)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _priceDetailsState.value = _priceDetailsState.value.copy(
                                gasolinePriceListToday = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _errorMessageSharedFlow.emit(result.message ?: "Success")

                        }

                        is Resource.Error -> {
                            _priceDetailsState.value = _priceDetailsState.value.copy(
                                gasolinePriceListToday = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _errorMessageSharedFlow.emit(result.message ?: "Unknown error")
                        }

                        is Resource.Loading -> {
                            _priceDetailsState.value = _priceDetailsState.value.copy(
                                gasolinePriceListToday = result.data ?: emptyList(),
                                isLoading = true
                            )
                            _errorMessageSharedFlow.emit(result.message ?: "Loading")
                        }
                    }
                }.launchIn(this)
        }

}