package com.example.testcoffe.order.domain.usecase

import com.example.testcoffe.order.domain.repository.CartRepository
import javax.inject.Inject

class UpdateItemCountUseCase @Inject constructor(
    private val repo: CartRepository
) {
    suspend operator fun invoke(id: Long, count: Int) = repo.updateItemCount(id, count)
}