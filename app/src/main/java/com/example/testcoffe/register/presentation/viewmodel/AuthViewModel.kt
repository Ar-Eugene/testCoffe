package com.example.testcoffe.register.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcoffe.register.domain.model.AuthToken
import com.example.testcoffe.register.domain.use_case.LoginUseCase
import com.example.testcoffe.register.domain.use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    var tokenState by mutableStateOf<Result<AuthToken>?>(null)
        private set

    fun login(login: String, password: String) {
        viewModelScope.launch {
            tokenState = runCatching { loginUseCase(login, password) }
        }
    }

    fun register(login: String, password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            tokenState = Result.failure(Exception("Пароли не совпадают"))
            return
        }

        viewModelScope.launch {
            tokenState = runCatching {
                registerUseCase(login, password)
            }
        }
    }

}