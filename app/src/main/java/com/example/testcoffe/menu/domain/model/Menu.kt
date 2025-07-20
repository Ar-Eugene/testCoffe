package com.example.testcoffe.menu.domain.model

data class Menu(
    val id: Long,
    val name: String,
    val imageURL: String,
    val price: Double,
    val count: Int = 0 // для количества в корзине
)
