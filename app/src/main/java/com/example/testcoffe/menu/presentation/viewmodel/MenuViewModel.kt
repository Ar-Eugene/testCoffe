package com.example.testcoffe.menu.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcoffe.menu.domain.model.Menu
import com.example.testcoffe.menu.domain.use_case.MenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val menuUseCase: MenuUseCase) : ViewModel() {

    private val _menuItem = MutableStateFlow<List<Menu>>(emptyList())
    val menuItem: StateFlow<List<Menu>> = _menuItem.asStateFlow()


    fun loadMenu(locationsId: Long, token: String) {
        Log.d("MenuViewModel", "Загрузка меню для локации: $locationsId")
        viewModelScope.launch {
            menuUseCase(locationsId, token)
                .catch { e -> Log.e("MenuViewModel", "Ошибка загрузки меню: ${e.message}", e) }
                .collect { menuList ->
                    Log.d("MenuViewModel", "Меню получено: $menuList")
                    _menuItem.value = menuList
                }
        }
    }
}