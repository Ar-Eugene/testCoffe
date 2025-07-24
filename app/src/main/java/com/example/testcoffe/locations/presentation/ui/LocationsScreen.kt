package com.example.testcoffe.locations.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.testcoffe.locations.domain.model.Location
import com.example.testcoffe.locations.presentation.viewmodel.LocationViewModel
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
fun LocationsScreen(
    token: String,
    navController: NavController = rememberNavController(), // <-- добавим аргумент
    viewModel: LocationViewModel = hiltViewModel()
) {
    val locations by viewModel.location.collectAsState()
    val userLocation by viewModel.userLocation.collectAsState()
    val isLoading = locations.isEmpty() || userLocation == null

    LaunchedEffect(token) {
        viewModel.loadUserLocation()
        viewModel.loadLocations(token)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            val (userLat, userLon) = userLocation!!

            LazyColumn(modifier = Modifier.padding(8.dp)) {
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
}

fun calculateDistance(
    userLat: Double, userLon: Double,
    cafeLat: Double, cafeLon: Double
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

@Composable
fun LocationItem(
    location: Location,
    userLat: Double,
    userLon: Double,
    onClick: () -> Unit
) {
    val distanceText = try {
        val distance = calculateDistance(userLat, userLon, location.latitude, location.longitude)
        if (distance > 1000) "${(distance / 1000).toInt()} км от вас" else "${distance.toInt()} м от вас"
    } catch (e: Exception) {
        "Ошибка"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = location.name, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = distanceText, style = MaterialTheme.typography.bodyMedium)
        }
    }
}