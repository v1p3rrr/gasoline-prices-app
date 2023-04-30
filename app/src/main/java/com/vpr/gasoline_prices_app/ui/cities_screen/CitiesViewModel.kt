package com.vpr.gasoline_prices_app.ui.cities_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpr.gasoline_prices_app.domain.repository.GasolinePriceRepository
import com.vpr.gasoline_prices_app.ui.dates_screen.DatesState
import com.vpr.gasoline_prices_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val repository: GasolinePriceRepository
) : ViewModel() {

    private val _citiesState = mutableStateOf(CitiesState())
    val citiesState: State<CitiesState> = _citiesState

    private val _errorMessageSharedFlow = MutableSharedFlow<String>()
    val errorMessageSharedFlow = _errorMessageSharedFlow.asSharedFlow()

    fun getCities() =
        viewModelScope.launch {
            repository.getCities()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _citiesState.value = _citiesState.value.copy(
                                citiesList = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _errorMessageSharedFlow.emit(result.message ?: "Success")

                        }

                        is Resource.Error -> {
                            _citiesState.value = _citiesState.value.copy(
                                citiesList = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _errorMessageSharedFlow.emit(result.message ?: "Unknown error")
                        }

                        is Resource.Loading -> {
                            _citiesState.value = _citiesState.value.copy(
                                citiesList = result.data ?: emptyList(),
                                isLoading = true
                            )
                            _errorMessageSharedFlow.emit(result.message ?: "Loading")
                        }
                    }
                }.launchIn(this)
        }

}