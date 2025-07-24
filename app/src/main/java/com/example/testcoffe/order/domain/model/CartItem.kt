package com.example.testcoffe.order.domain.model

data class CartItem(
    val id: Long,
    val name: String,
    val price: Double,
    val count: Int
)