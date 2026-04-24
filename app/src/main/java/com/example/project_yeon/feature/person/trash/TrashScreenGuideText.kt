package com.example.project_yeon.feature.person.trash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TrashScreenGuideText(
    fontNanumPen: FontFamily
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "인연을 선택 후, 삭제 또는 복원하세요.",
            fontFamily = fontNanumPen,
            fontSize = 18.sp,
            color = Color(0xFF7B8EDC)
        )

        Text(
            text = "해당 보관함에서 삭제되면 삭제되는 인연은, 더 이상 복구가 불가능합니다.",
            fontFamily = fontNanumPen,
            fontSize = 12.sp,
            color = Color(0xFFE38C8C)
        )
    }
}