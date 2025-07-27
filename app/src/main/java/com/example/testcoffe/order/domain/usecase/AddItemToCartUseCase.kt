package com.example.testcoffe.order.domain.usecase

import com.example.testcoffe.order.domain.model.CartItem
import com.example.testcoffe.order.domain.repository.CartRepository
import javax.inject.Inject

class AddItemToCartUseCase @Inject constructor(
    private val repo: CartRepository
) {
    suspend operator fun invoke(item: CartItem) = repo.addItem(item)
}