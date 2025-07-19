package com.example.testcoffe.register.domain.use_case

import com.example.testcoffe.register.domain.model.AuthToken
import com.example.testcoffe.register.domain.repository.AuthRepository
import jakarta.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(login: String, password: String): AuthToken {
        return repository.login(login, password)
    }
}