package com.example.testcoffe.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testcoffe.locations.presentation.ui.LocationsScreen
import com.example.testcoffe.menu.presentation.ui.MenuScreen
import com.example.testcoffe.order.presentation.ui.OrderScreen
import com.example.testcoffe.order.presentation.viewmodel.CartViewModel
import com.example.testcoffe.register.presentation.ui.AuthScreen
import com.example.testcoffe.register.presentation.ui.LoginScreen
import com.example.testcoffe.register.presentation.viewmodel.AuthViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: AuthViewModel = hiltViewModel()
    val startDestination = if (viewModel.isRegistered()) "login" else "auth"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("auth") {
            AuthScreen(onNavigateToLogin = {
                navController.navigate("login") {
                    popUpTo("auth") { inclusive = true }
                }
            })
        }
        composable("login") {
            LoginScreen(onNavigateToLocations = { token ->
                navController.navigate("locations/$token") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }
        composable(
            "locations/{token}",
            arguments = listOf(navArgument("token") { type = NavType.StringType })
        ) { backStackEntry ->
            val token = backStackEntry.arguments?.getString("token") ?: ""
            LocationsScreen(token = token, navController = navController)
        }

        composable(
            "menu/{locationId}/{token}",
            arguments = listOf(
                navArgument("locationId") { type = NavType.LongType },
                navArgument("token") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val locationId = backStackEntry.arguments?.getLong("locationId") ?: -1L
            val token = backStackEntry.arguments?.getString("token") ?: ""
            if (locationId != -1L) {
                MenuScreen(locationId = locationId, token = token, navController = navController)
            }
        }

        composable("order") {
            val cartViewModel: CartViewModel = hiltViewModel()
            val cartItems by cartViewModel.cartItems.collectAsState()

            OrderScreen(
                orderItems = cartItems,
                navController = navController
            )
        }
    }
}