package com.example.testcoffe.register.data.network

import com.example.testcoffe.register.data.dto.AuthResponseDto
import com.example.testcoffe.register.data.dto.LoginRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body requeat: LoginRequestDto): AuthResponseDto

    @POST("auth/register")
    suspend fun register(@Body requeat: LoginRequestDto): AuthResponseDto
}