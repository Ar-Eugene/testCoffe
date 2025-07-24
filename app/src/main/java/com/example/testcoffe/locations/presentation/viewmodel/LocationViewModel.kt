package com.example.testcoffe.locations.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcoffe.locations.domain.model.Location
import com.example.testcoffe.locations.domain.use_case.LocationUseCase
import com.example.testcoffe.locations.presentation.LocationTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase,
    private val locationTracker: LocationTracker
) :
    ViewModel() {

    private val _location = MutableStateFlow<List<Location>>(emptyList())
    val location: StateFlow<List<Location>> = _location.asStateFlow()


    private val _userLocation = MutableStateFlow<Pair<Double, Double>?>(null)
    val userLocation: StateFlow<Pair<Double, Double>?> = _userLocation.asStateFlow()

    fun loadLocations(token: String) {
        viewModelScope.launch {
            locationUseCase.invoke(token)
                .catch { e ->
                    Log.e(
                        "LocationViewModel",
                        "Ошибка загрузки локаций: ${e.message}",
                        e
                    )
                }
                .collect { _location.value = it }
        }
    }

    fun loadUserLocation() {
        viewModelScope.launch {
            val location = locationTracker.getCurrentLocation()
            if (location != null) {
                _userLocation.value = location.latitude to location.longitude
                Log.d(
                    "LocationViewModel",
                    "User location: ${location.latitude}, ${location.longitude}"
                )
            } else {
                Log.e("LocationViewModel", "User location is null")
            }
        }
    }
}
