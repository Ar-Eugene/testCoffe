package com.example.testcoffe.locations.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcoffe.locations.domain.model.Location
import com.example.testcoffe.locations.domain.use_case.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val locationUseCase: LocationUseCase) :
    ViewModel() {

    private val _location = MutableStateFlow<List<Location>>(emptyList())
    val location: StateFlow<List<Location>> = _location.asStateFlow()

    fun loadLocations(token: String) {
        viewModelScope.launch {
            locationUseCase.invoke(token)
                .catch { e -> Log.e("LocationViewModel", "Ошибка загрузки локаций: ${e.message}", e) }
                .collect { _location.value = it }
        }
    }
}