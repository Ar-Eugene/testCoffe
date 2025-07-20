package com.example.testcoffe.register.domain.model

data class AuthToken(
    val token: String,
    val tokenLifetime: Long
)
