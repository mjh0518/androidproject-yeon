package com.example.project_yeon.feature.person.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun DetailSensitiveInfoSection(
    person: DetailPersonUiModel,
    modifier: Modifier = Modifier
) {
    val fontNanumGyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))
    val fontNanumPen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "기타 정보",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = YeonTextOnBackGround,
            fontFamily = fontNanumGyuri,
            fontSize = 22.sp
        )

        SensitiveInfoBox(
            phone = person.phoneMasked,
            address = person.addressMasked,
            sns = person.snsMasked,
            titleFont = fontNanumGyuri,
            bodyFont = fontNanumPen
        )
    }
}