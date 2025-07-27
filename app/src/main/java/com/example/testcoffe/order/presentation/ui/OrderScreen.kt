package com.example.testcoffe.order.presentation.ui

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testcoffe.R
import com.example.testcoffe.core.theme.BigTextColor
import com.example.testcoffe.core.theme.ButtomTextColor
import com.example.testcoffe.core.theme.ButtonBackgroundColor
import com.example.testcoffe.core.theme.DividerColor
import com.example.testcoffe.core.theme.FocusedBorderColor
import com.example.testcoffe.core.theme.IconBackColor
import com.example.testcoffe.core.theme.StatusBarColor
import com.example.testcoffe.order.domain.model.CartItem
import com.example.testcoffe.order.presentation.viewmodel.CartViewModel

@Composable
fun OrderScreen(
    orderItems: List<CartItem>,
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        TopAppBarStateOrder(
            onClick = { navController.popBackStack() },
            text = stringResource(R.string.order)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(R.dimen._16dp))
        ) {

            Column(modifier = Modifier.fillMaxSize()) {

            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = dimensionResource(R.dimen._16dp)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen._8dp))
            ) {
                // Список товаров
                items(orderItems) { item ->
                    OrderItemCard(
                        item = item,
                        onIncrement = { cartViewModel.updateItemCount(item.id, item.count + 1) },
                        onDecrement = {
                            if (item.count > 1) {
                                cartViewModel.updateItemCount(item.id, item.count - 1)
                            } else {
                                cartViewModel.updateItemCount(item.id, 0)
                            }
                        }
                    )
                }
                item {
                    WaitText()
                }
            }
        }

        ButtonToPay(
            text = stringResource(R.string.pay),
            onClick = {
                Toast.makeText(context, "Заказ оплачен", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

// Верхняя часть с заголовком
@Composable
fun TopAppBarStateOrder(onClick: () -> Unit, text: String) {
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
                    contentDescription = "кнопка перехода на экран меню",
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

// Текст о времени ожидания
@Composable
fun WaitText() {
    Text(
        text = stringResource(R.string.wait),
        color = FocusedBorderColor,
        fontSize = dimensionResource(R.dimen.text_24sp).value.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen._16dp))
    )
}

@Composable
fun ButtonToPay(
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
