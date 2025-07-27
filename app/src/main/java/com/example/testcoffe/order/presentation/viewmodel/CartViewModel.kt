package com.example.testcoffe.order.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcoffe.menu.domain.model.Menu
import com.example.testcoffe.order.domain.model.CartItem
import com.example.testcoffe.order.domain.usecase.AddItemToCartUseCase
import com.example.testcoffe.order.domain.usecase.ClearCartUseCase
import com.example.testcoffe.order.domain.usecase.GetCartUseCase
import com.example.testcoffe.order.domain.usecase.UpdateItemCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    getCartUseCase: GetCartUseCase,
    private val addItemToCartUseCase: AddItemToCartUseCase,
    private val updateItemCountUseCase: UpdateItemCountUseCase,
    private val clearCartUseCase: ClearCartUseCase
) : ViewModel() {

    val cartItems = getCartUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addItem(menu: Menu) {
        viewModelScope.launch {
            addItemToCartUseCase(
                CartItem(
                    id = menu.id,
                    name = menu.name,
                    price = menu.price,
                    count = 1
                )
            )
        }
    }

    fun updateItemCount(id: Long, count: Int) {
        viewModelScope.launch {
            updateItemCountUseCase(id, count)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            clearCartUseCase()
        }
    }
}