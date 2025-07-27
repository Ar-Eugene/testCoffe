package com.example.testcoffe.order.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testcoffe.R
import com.example.testcoffe.core.theme.BigTextColor
import com.example.testcoffe.core.theme.CardColor
import com.example.testcoffe.core.theme.FocusedBorderColor
import com.example.testcoffe.core.theme.IconBackColor
import com.example.testcoffe.core.theme.PlaceholderColor
import com.example.testcoffe.order.domain.model.CartItem

@Composable
fun OrderItemCard(
    item: CartItem,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(R.dimen._6dp)),
        shape = RoundedCornerShape(dimensionResource(R.dimen._6dp)),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = CardColor
        )
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(R.dimen._16dp))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    color = BigTextColor,
                    fontSize = dimensionResource(R.dimen.text_18sp).value.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${(item.price * item.count).toInt()} руб",
                    color = PlaceholderColor,
                    fontSize = dimensionResource(R.dimen.text_16sp).value.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = onDecrement,

                    ) {
                    Icon(
                        painter = painterResource(R.drawable.remove),
                        contentDescription = "Уменьшить",
                        tint = FocusedBorderColor
                    )
                }

                Text(
                    text = "${item.count}",
                    color = IconBackColor,
                    fontSize = dimensionResource(R.dimen.text_16sp).value.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.width(24.dp),
                    textAlign = TextAlign.Center
                )

                IconButton(
                    onClick = onIncrement,

                    ) {
                    Icon(
                        painter = painterResource(R.drawable.add),
                        contentDescription = "Увеличить",
                        tint = FocusedBorderColor
                    )
                }
            }
        }
    }
}
