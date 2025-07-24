package com.example.testcoffe.register.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcoffe.register.domain.model.AuthToken
import com.example.testcoffe.register.domain.use_case.LoginUseCase
import com.example.testcoffe.register.domain.use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    app: Application,
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : AndroidViewModel(app) {

    var tokenState by mutableStateOf<Result<AuthToken>?>(null)
        private set

    private val prefs = app.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun isRegistered(): Boolean = prefs.getBoolean("registered", false)

    fun setRegistered() {
        prefs.edit().putBoolean("registered", true).apply()
    }

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
            tokenState?.onSuccess {
                setRegistered()
            }
        }
    }
}