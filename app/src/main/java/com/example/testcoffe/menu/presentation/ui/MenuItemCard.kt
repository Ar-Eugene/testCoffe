package com.example.testcoffe.menu.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.testcoffe.R
import com.example.testcoffe.core.theme.CardColor
import com.example.testcoffe.core.theme.FocusedBorderColor
import com.example.testcoffe.core.theme.IconBackColor
import com.example.testcoffe.core.theme.PlaceholderColor
import com.example.testcoffe.menu.domain.model.Menu

@Composable
fun MenuItemCard(
    menu: Menu,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
) {
    ElevatedCard(
        shape = RoundedCornerShape(dimensionResource(R.dimen._6dp)),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        )
    ) {
        Column {
            // Картинка
            AsyncImage(
                model = menu.imageURL,
                contentDescription = menu.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(137.dp),
                contentScale = ContentScale.Crop,
                onError = { error -> Log.e("MenuImage", "Ошибка Coil: $error") }
            )
            // Название
            Text(
                text = menu.name,
                color = PlaceholderColor,
                fontSize = dimensionResource(R.dimen.text_16sp).value.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 12.dp, top = 10.dp),
                textAlign = TextAlign.Start,
            )
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                // Цена
                Text(
                    text = "${menu.price.toInt()} руб",
                    color = FocusedBorderColor,
                    fontSize = dimensionResource(R.dimen.text_18sp).value.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 4.dp),
                )

                // Счетчик
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onDecrement,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.remove),
                            contentDescription = "Уменьшить",
                            tint = CardColor
                        )
                    }

                    Text(
                        text = "${menu.count}",
                        color = IconBackColor,
                        fontSize = dimensionResource(R.dimen.text_16sp).value.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    IconButton(
                        onClick = onIncrement,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.add),
                            contentDescription = "Увеличить",
                            tint = CardColor
                        )
                    }
                }
            }
        }
    }
}