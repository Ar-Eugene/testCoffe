package com.example.testcoffe.locations.presentation.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.testcoffe.R
import com.example.testcoffe.core.theme.BigTextColor
import com.example.testcoffe.core.theme.ButtomTextColor
import com.example.testcoffe.core.theme.ButtonBackgroundColor
import com.example.testcoffe.core.theme.DividerColor
import com.example.testcoffe.core.theme.IconBackColor
import com.example.testcoffe.core.theme.StatusBarColor
import com.example.testcoffe.locations.presentation.viewmodel.LocationViewModel
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
fun LocationsScreen(
    token: String,
    navController: NavController = rememberNavController(),
    viewModel: LocationViewModel = hiltViewModel(),
    context: Context = LocalContext.current, // Добавляем контекст для Toast
) {
    val locations by viewModel.location.collectAsState()
    val userLocation by viewModel.userLocation.collectAsState()
    val isLoading = locations.isEmpty() || userLocation == null

    LaunchedEffect(token) {
        viewModel.loadUserLocation()
        viewModel.loadLocations(token)
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        TopAppBarState(
            onClick = {
                navController.navigate("login") {
                    popUpTo("locations/{token}") { inclusive = true }
                }
            },
            text = stringResource(R.string.coffe)
        )

        Box(
            modifier = Modifier
                .weight(1f) // Занимает все доступное пространство между AppBar и кнопкой
                .padding(horizontal = dimensionResource(R.dimen._16dp))
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                val (userLat, userLon) = userLocation!!

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = dimensionResource(R.dimen._16dp)),

                    ) {
                    items(locations) { location ->
                        LocationItem(
                            location = location,
                            userLat = userLat,
                            userLon = userLon,
                            onClick = {
                                navController.navigate("menu/${location.id}/$token")
                            }
                        )
                    }
                }
            }
        }

        // Фиксированная кнопка внизу экрана
        Button(
            text = stringResource(R.string.in_map),
            onClick = {
                Toast.makeText(
                    context, // Используем полученный контекст
                    "Функция будет доступна после релиза",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen._16dp), vertical = dimensionResource(R.dimen._8dp))
        )
    }
}

fun calculateDistance(
    userLat: Double, userLon: Double,
    cafeLat: Double, cafeLon: Double,
): Double {
    val earthRadius = 6371000.0 // метры
    val dLat = Math.toRadians(cafeLat - userLat)
    val dLon = Math.toRadians(cafeLon - userLon)
    val a = sin(dLat / 2).pow(2.0) +
            cos(Math.toRadians(userLat)) * cos(Math.toRadians(cafeLat)) *
            sin(dLon / 2).pow(2.0)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return earthRadius * c // в метрах
}

// Верхняя часть с заголовком
@Composable
fun TopAppBarState(onClick: () -> Unit, text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(StatusBarColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
        ) {
            // Кнопка назад в левом углу
            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 20.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "кнопка перехода на выход",
                    tint = IconBackColor
                )
            }
            // Текст по центру
            Text(
                text = text,
                color = BigTextColor,
                fontSize = dimensionResource(R.dimen.text_18sp).value.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        // Разделительная полоса
        Divider(
            color = DividerColor,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}


@Composable
fun Button(
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