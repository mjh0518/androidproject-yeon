package com.example.project_yeon.core.ui.etc

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    rating: Int,
    maxRating: Int = 5,
    onRatingChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (index in 1..maxRating) {
            val isSelected = index <= rating

            Icon(
                imageVector = if (isSelected) {
                    Icons.Filled.Star
                } else {
                    Icons.Outlined.Star
                },
                contentDescription = "별점 $index",
                tint = if (isSelected) Color(0xFFFFC107) else Color.Gray,
                modifier = Modifier
                    .size(36.dp)
                    .padding(4.dp)
                    .clickable { onRatingChanged(index) }
            )
        }
    }
}