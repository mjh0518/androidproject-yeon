package com.example.project_yeon.feature.person.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

    val phone = person.phoneMasked?.trim().orEmpty()
    val address = person.addressMasked?.trim().orEmpty()
    val sns = person.snsMasked?.trim().orEmpty()

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
            phone = phone,
            address = address,
            sns = sns,
            titleFont = fontNanumGyuri,
            bodyFont = fontNanumPen
        )
    }
}