package com.example.testcoffe.register.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testcoffe.R
import com.example.testcoffe.core.theme.BigTextColor
import com.example.testcoffe.core.theme.ButtomTextColor
import com.example.testcoffe.core.theme.ButtonBackgroundColor
import com.example.testcoffe.core.theme.DividerColor
import com.example.testcoffe.core.theme.FocusedBorderColor
import com.example.testcoffe.core.theme.PlaceholderColor
import com.example.testcoffe.core.theme.StatusBarColor
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

        StatusBartext()

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

// Верхняя часть с заголовком
@Composable
fun StatusBartext() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(StatusBarColor) // Серый фон как на скриншоте
            .statusBarsPadding()
            .padding(vertical = dimensionResource(R.dimen.padding_18dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.register),
            color = BigTextColor,
            fontSize = dimensionResource(R.dimen.text_18sp).value.sp,
            fontWeight = FontWeight.Bold
        )
    }

    // Разделительная полоса
    Divider(
        color = DividerColor,
        thickness = 1.dp,
        modifier = Modifier.fillMaxWidth()
    )
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

        RegisterButton(
            onClick = { onRegisterClick(email, password, confirmPassword) },
        )

    }
}

// текст над полями ввода
@Composable
fun TextOverextField(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text,
        color = PlaceholderColor,
        fontSize = dimensionResource(R.dimen.text_15sp).value.sp,
        fontWeight = fontWeight, // Применяем переданный вес
        modifier = modifier
            .padding(bottom = dimensionResource(R.dimen._8dp))
            .fillMaxWidth()
            .wrapContentWidth(Alignment.Start)  // Выравнивание по левому краю
    )
}

// Общий OutlinedTextField для всех полей
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = PlaceholderColor) },
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(bottom = dimensionResource(R.dimen._24dp))
            .border(
                width = 2.dp,
                color = FocusedBorderColor,
                shape = RoundedCornerShape(dimensionResource(R.dimen._24dp))
            ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedTextColor = PlaceholderColor,
            unfocusedTextColor = PlaceholderColor
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}

// кнопка для регистрации
@Composable
fun RegisterButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(ButtonBackgroundColor)
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 12.dp),
            text = stringResource(R.string.register_button),
            fontSize = dimensionResource(R.dimen.text_18sp).value.sp,
            fontWeight = FontWeight.Bold,
            color = ButtomTextColor
        )
    }
}