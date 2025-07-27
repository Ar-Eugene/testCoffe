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
fun LoginScreen(
    onNavigateToLocations: (String) -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
) {

    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    val tokenState = viewModel.tokenState

    LaunchedEffect(tokenState) {
        isLoading = false
        tokenState?.onSuccess {
            onNavigateToLocations(it.token)
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

        TopAppBarText(text = stringResource(R.string.enter))

        // Основное содержимое
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(horizontal = dimensionResource(R.dimen.padding_18dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputTextForEnter(onRegisterClick = { email, password ->
                viewModel.login(
                    email,
                    password
                )
            })
        }


    }
}

@Composable
fun InputTextForEnter(onRegisterClick: (String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
        Spacer(modifier = Modifier.height(30.dp))

        RegisterOrInputButton(
            onClick = { onRegisterClick(email, password) },
            text = stringResource(R.string.enter)
        )
    }
}
