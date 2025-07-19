package com.example.testcoffe.register.data.dto

data class AuthResponseDto(
    val token:String,
    val tokenLifetime: Long
)