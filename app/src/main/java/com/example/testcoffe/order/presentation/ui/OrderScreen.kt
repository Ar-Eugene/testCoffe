package com.example.testcoffe.order.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.testcoffe.order.domain.model.CartItem

@Composable
fun OrderScreen(
    orderItems: List<CartItem>,
    onPayClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Ваш заказ", modifier = Modifier.padding(bottom = 16.dp))
        orderItems.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(item.name)
                    Text("${item.price.toInt()} руб")
                }
                Text("× ${item.count}")
                Text("${(item.price * item.count).toInt()} руб")
            }
        }

        val total = orderItems.sumOf { it.price * it.count }

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            "Время ожидания заказа\n15 минут!\nСпасибо, что выбрали нас!",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        )
        Button(
            onClick = onPayClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Оплатить ($total руб)")
        }
    }
}
