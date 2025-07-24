package com.example.testcoffe.menu.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testcoffe.menu.presentation.viewmodel.MenuViewModel
import com.example.testcoffe.order.presentation.viewmodel.CartViewModel

@Composable
fun MenuScreen(
    locationId: Long,
    token: String,
    navController: NavController,
    menuViewModel: MenuViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val menuItems by menuViewModel.menuItem.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()

    LaunchedEffect(locationId) {
        menuViewModel.loadMenu(locationId, token)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(menuItems) { item ->
                val cartItem = cartItems.find { it.id == item.id }
                val count = cartItem?.count ?: 0

                MenuItemCard(
                    menu = item.copy(count = count),
                    onIncrement = {
                        cartViewModel.addItem(item)
                        cartViewModel.updateItemCount(item.id, count + 1)
                    },
                    onDecrement = {
                        if (count > 1) {
                            cartViewModel.updateItemCount(item.id, count - 1)
                        } else {
                            cartViewModel.updateItemCount(item.id, 0)
                        }
                    }
                )
            }
        }

        Button(
            onClick = { navController.navigate("order") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Перейти к оплате")
        }
    }
}
