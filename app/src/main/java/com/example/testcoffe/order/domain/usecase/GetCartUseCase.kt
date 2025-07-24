package com.example.testcoffe.order.domain.usecase

import com.example.testcoffe.order.domain.model.CartItem
import com.example.testcoffe.order.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val repo: CartRepository
) {
    operator fun invoke(): Flow<List<CartItem>> = repo.getCart()
}