package com.example.testcoffe.locations.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testcoffe.R
import com.example.testcoffe.core.theme.BigTextColor
import com.example.testcoffe.core.theme.CardColor
import com.example.testcoffe.core.theme.PlaceholderColor
import com.example.testcoffe.locations.domain.model.Location

@Composable
fun LocationItem(
    location: Location,
    userLat: Double,
    userLon: Double,
    onClick: () -> Unit,
) {
    val distanceText = try {
        val distance = calculateDistance(userLat, userLon, location.latitude, location.longitude)
        if (distance > 1000) "${(distance / 1000).toInt()} км от вас" else "${distance.toInt()} м от вас"
    } catch (e: Exception) {
        "Ошибка"
    }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(R.dimen._6dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(dimensionResource(R.dimen._6dp)),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = CardColor
        )
    ) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen._16dp))) {
            Text(
                text = location.name,
                color = BigTextColor,
                fontSize = dimensionResource(R.dimen.text_18sp).value.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = distanceText,
                color = PlaceholderColor,
                fontSize = dimensionResource(R.dimen.text_16sp).value.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}