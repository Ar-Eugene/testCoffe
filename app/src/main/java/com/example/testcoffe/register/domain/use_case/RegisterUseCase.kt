package com.example.testcoffe.register.domain.use_case

import com.example.testcoffe.register.domain.model.AuthToken
import com.example.testcoffe.register.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(login: String, password: String): AuthToken {
        return repository.register(login, password)
    }
}