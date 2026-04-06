package com.example.project_yeon.core.ui.etc

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
            Icon(
                imageVector = if (index <= rating) Icons.Default.Star else Icons.Default.Star,
                contentDescription = "별점 $index",
                tint = Color.Gray,
                modifier = Modifier
                    .size(48.dp)
                    .padding(4.dp)
                    .clickable { onRatingChanged(index) }
            )
        }
    }
}