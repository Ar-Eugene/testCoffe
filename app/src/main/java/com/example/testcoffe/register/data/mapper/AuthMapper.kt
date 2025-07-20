package com.example.testcoffe.register.data.mapper

import com.example.testcoffe.register.data.dto.AuthResponseDto
import com.example.testcoffe.register.domain.model.AuthToken

fun AuthResponseDto.toDomain(): AuthToken = AuthToken(
    token = token,
    tokenLifetime = tokenLifetime
)