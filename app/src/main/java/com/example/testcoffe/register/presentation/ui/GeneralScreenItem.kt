package com.example.testcoffe.register.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testcoffe.R
import com.example.testcoffe.core.theme.BigTextColor
import com.example.testcoffe.core.theme.ButtomTextColor
import com.example.testcoffe.core.theme.ButtonBackgroundColor
import com.example.testcoffe.core.theme.DividerColor
import com.example.testcoffe.core.theme.FocusedBorderColor
import com.example.testcoffe.core.theme.PlaceholderColor
import com.example.testcoffe.core.theme.StatusBarColor


// Верхняя часть с заголовком
@Composable
fun StatusBarText(text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(StatusBarColor) // Серый фон как на скриншоте
            .statusBarsPadding()
            .padding(vertical = dimensionResource(R.dimen.padding_18dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
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
fun RegisterOrInputButton(
    onClick: () -> Unit,
    text: String,
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
            text = text,
            fontSize = dimensionResource(R.dimen.text_18sp).value.sp,
            fontWeight = FontWeight.Bold,
            color = ButtomTextColor
        )
    }
}