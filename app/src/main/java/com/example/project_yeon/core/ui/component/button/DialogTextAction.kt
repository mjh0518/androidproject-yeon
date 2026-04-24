package com.example.project_yeon.core.ui.component.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DialogTextAction(
    text: String,
    color: Color,
    fontNanumPen: FontFamily,
    onClick: () -> Unit
) {
    Text(
        text = text,
        modifier = Modifier
            .clickable { onClick() }
            .padding(start = 18.dp),
        fontFamily = fontNanumPen,
        fontSize = 16.sp,
        color = color
    )
}