package com.example.project_yeon.feature.person.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun DetailSensitiveInfoSection(
    person: DetailPersonUiModel,
    unlocked: Boolean,
    onUnlockClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fontNanumGyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))
    val fontNanumPen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))

    val phone = if (unlocked) {
        person.phone?.trim().orEmpty().ifBlank { "등록된 연락처가 없습니다." }
    } else {
        "••••••••"
    }

    val address = if (unlocked) {
        person.address?.trim().orEmpty().ifBlank { "등록된 거주지가 없습니다." }
    } else {
        "••••••••"
    }

    val sns = if (unlocked) {
        person.sns?.trim().orEmpty().ifBlank { "등록된 SNS가 없습니다." }
    } else {
        "••••••••"
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(48.dp))

            Text(
                text = "기타 정보",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                color = YeonTextOnBackGround,
                fontFamily = fontNanumGyuri,
                fontSize = 22.sp
            )

            IconButton(
                onClick = onUnlockClick,
                enabled = !unlocked
            ) {
                Icon(
                    imageVector = if (unlocked) Icons.Default.LockOpen else Icons.Default.Lock,
                    contentDescription = if (unlocked) "보안 정보 잠금 해제됨" else "보안 정보 보기",
                    tint = if (unlocked) YeonTextMuted else YeonTextOnBackGround
                )
            }
        }

        SensitiveInfoBox(
            phone = phone,
            address = address,
            sns = sns,
            titleFont = fontNanumGyuri,
            bodyFont = fontNanumPen
        )
    }
}