package com.example.testcoffe.order.data.repositoryimpl

import com.example.testcoffe.order.domain.model.CartItem
import com.example.testcoffe.order.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor() : CartRepository {
    private val cart = MutableStateFlow<List<CartItem>>(emptyList())

    override fun getCart(): Flow<List<CartItem>> = cart

    override suspend fun addItem(item: CartItem) {
        cart.value = cart.value.toMutableList().apply {
            val existing = find { it.id == item.id }
            if (existing != null) {
                val updated = existing.copy(count = existing.count + item.count)
                remove(existing)
                add(updated)
            } else {
                add(item)
            }
        }
    }

    override suspend fun updateItemCount(id: Long, count: Int) {
        cart.value = cart.value.map {
            if (it.id == id) it.copy(count = count) else it
        }.filter { it.count > 0 }
    }

    override suspend fun clearCart() {
        cart.value = emptyList()
    }
}