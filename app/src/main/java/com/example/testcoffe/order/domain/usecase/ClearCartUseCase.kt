package com.example.testcoffe.order.domain.usecase

import com.example.testcoffe.order.domain.repository.CartRepository
import javax.inject.Inject

class ClearCartUseCase @Inject constructor(
    private val repo: CartRepository
) {
    suspend operator fun invoke() = repo.clearCart()
}