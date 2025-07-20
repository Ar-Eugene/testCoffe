package com.example.testcoffe.register.data.repositoryImpl

import com.example.testcoffe.register.data.dto.LoginRequestDto
import com.example.testcoffe.register.data.mapper.toDomain
import com.example.testcoffe.register.data.network.AuthApi
import com.example.testcoffe.register.domain.model.AuthToken
import com.example.testcoffe.register.domain.repository.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(private val api: AuthApi) : AuthRepository {
    override suspend fun login(login: String, password: String): AuthToken {
        val response = api.login(LoginRequestDto(login, password))
        return response.toDomain()
    }

    override suspend fun register(login: String, password: String): AuthToken {
        val response = api.register(LoginRequestDto(login, password))
        return response.toDomain()
    }
}