package com.example.testcoffe.menu.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testcoffe.menu.domain.model.Menu
import com.example.testcoffe.menu.presentation.viewmodel.MenuViewModel

@Composable
fun MenuScreen(
    locationId: Long,
    viewModel: MenuViewModel = hiltViewModel(),
    onCheckoutClick: () -> Unit = {}
) {
    val menuList by viewModel.menuItem.collectAsState()

    LaunchedEffect(locationId) {
        viewModel.loadMenu(locationId)
    }

    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f)
        ) {
            items(menuList) { item ->
                MenuItemCard(
                    menu = item,
                    onIncrement = { viewModel.incrementItem(item.id) },
                    onDecrement = { viewModel.decrementItem(item.id) }
                )
            }
        }

        Button(
            onClick = onCheckoutClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E342F))
        ) {
            Text("Перейти к оплате", color = Color.White)
        }
    }
}