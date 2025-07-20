package com.example.testcoffe.menu.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcoffe.menu.domain.model.Menu
import com.example.testcoffe.menu.domain.repository.MenuRepository
import com.example.testcoffe.menu.domain.use_case.MenuUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val menuUseCase: MenuUseCase) : ViewModel() {

    private val _menuItem = MutableStateFlow<List<Menu>>(emptyList())
    val menuItem: StateFlow<List<Menu>> = _menuItem.asStateFlow()

    fun loadMenu(locationsId: Long) {
        viewModelScope.launch {
            menuUseCase(locationsId)
                .catch { e -> "Ошибка" }
                .collect { _menuItem.value = it }
        }
    }
}