package com.vpr.gasoline_prices_app.ui.dates_screen

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

class DatesViewModel @Inject constructor(
    private val repository: GasolinePriceRepository
) : ViewModel() {

    private val _datesState = mutableStateOf(DatesState())
    val datesState: State<DatesState> = _datesState

    private val _errorMessageSharedFlow = MutableSharedFlow<String>()
    val errorMessageSharedFlow = _errorMessageSharedFlow.asSharedFlow()

    fun getGasolinePricesListByCityAndDateRange(city: String, dateStart: String, dateEnd: String) =
        viewModelScope.launch {
            repository.getGasolinePricesListByCityAndDateRange(city, dateStart, dateEnd)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _datesState.value = datesState.value.copy(
                                gasolinePriceList = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _errorMessageSharedFlow.emit(result.message ?: "Success")

                        }

                        is Resource.Error -> {
                            _datesState.value = datesState.value.copy(
                                gasolinePriceList = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _errorMessageSharedFlow.emit(result.message ?: "Unknown error")
                        }

                        is Resource.Loading -> {
                            _datesState.value = datesState.value.copy(
                                gasolinePriceList = result.data ?: emptyList(),
                                isLoading = true
                            )
                            _errorMessageSharedFlow.emit(result.message ?: "Loading")
                        }
                    }
                }.launchIn(this)
        }
}