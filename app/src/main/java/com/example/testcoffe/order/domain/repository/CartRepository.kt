package com.example.testcoffe.order.domain.repository

import com.example.testcoffe.order.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCart(): Flow<List<CartItem>>
    suspend fun addItem(item: CartItem)
    suspend fun updateItemCount(id: Long, count: Int)
    suspend fun clearCart()
}