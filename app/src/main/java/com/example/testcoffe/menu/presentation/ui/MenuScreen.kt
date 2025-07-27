package com.example.testcoffe.menu.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testcoffe.R
import com.example.testcoffe.core.theme.BigTextColor
import com.example.testcoffe.core.theme.ButtomTextColor
import com.example.testcoffe.core.theme.ButtonBackgroundColor
import com.example.testcoffe.core.theme.DividerColor
import com.example.testcoffe.core.theme.IconBackColor
import com.example.testcoffe.core.theme.StatusBarColor
import com.example.testcoffe.menu.presentation.viewmodel.MenuViewModel
import com.example.testcoffe.order.presentation.viewmodel.CartViewModel

@Composable
fun MenuScreen(
    locationId: Long,
    token: String,
    navController: NavController,
    menuViewModel: MenuViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
) {
    val menuItems by menuViewModel.menuItem.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()

    LaunchedEffect(locationId) {
        menuViewModel.loadMenu(locationId, token)
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        TopAppBarStatee(
            onClick = {
                navController.popBackStack()

            },
            text = stringResource(R.string.menu)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(dimensionResource(R.dimen._16dp)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._16dp)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._16dp))
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
        ButtonToOrder(
            onClick = { navController.navigate("order") },
            text = stringResource(R.string.go_to_order)
        )

    }
}

// Верхняя часть с заголовком
@Composable
fun TopAppBarStatee(onClick: () -> Unit, text: String) {
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
fun ButtonToOrder(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen._16dp)),
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