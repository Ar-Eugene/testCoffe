package com.example.testcoffe.register.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testcoffe.R
import com.example.testcoffe.register.presentation.viewmodel.AuthViewModel

@Composable
fun AuthScreen(
    onNavigateToLogin: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    val tokenState = viewModel.tokenState

    LaunchedEffect(tokenState) {
        isLoading = false
        tokenState?.onSuccess {
            onNavigateToLogin()
        }?.onFailure {
            error = it.message
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        StatusBarText(text = stringResource(R.string.register))

        // Основное содержимое
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(horizontal = dimensionResource(R.dimen.padding_18dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputFields(
                onRegisterClick = { email, password, confirmPassword ->
                    isLoading = true
                    error = null
                    viewModel.register(email, password, confirmPassword)
                }
            )
        }
    }
}

@Composable
fun InputFields(
    onRegisterClick: (String, String, String) -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Поле - Email
        TextOverextField(stringResource(R.string.e_mail))

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email",
            keyboardType = KeyboardType.Email
        )

        // Поле - Пароль
        TextOverextField(stringResource(R.string.password))

        CustomTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "Пароль",
            isPassword = true
        )

        // Поле - Подтверждение пароля
        TextOverextField(stringResource(R.string.confirmPassword))

        CustomTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = "Подтвердите пароль",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(30.dp))

        RegisterOrInputButton(
            onClick = { onRegisterClick(email, password, confirmPassword) },
            text = stringResource(R.string.register_button)
        )

    }
}

