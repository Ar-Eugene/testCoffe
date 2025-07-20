package com.example.testcoffe.register.domain.repository

import com.example.testcoffe.register.domain.model.AuthToken

interface AuthRepository {
    suspend fun login(login: String, password: String): AuthToken
    suspend fun register(login: String, password: String): AuthToken
}