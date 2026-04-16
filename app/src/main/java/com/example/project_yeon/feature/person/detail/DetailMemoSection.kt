package com.example.project_yeon.feature.person.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun DetailMemoSection(
    memo: String,
    modifier: Modifier = Modifier
) {
    val fontNanumGyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))
    val fontNanumPen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))

    var isMemoExpanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MemorySectionTitleRow(
            title = "기타 메모",
            expanded = isMemoExpanded,
            onToggle = { isMemoExpanded = !isMemoExpanded },
            fontFamily = fontNanumGyuri
        )

        if (isMemoExpanded) {
            MemoryContentBox {
                Text(
                    text = if (memo.isBlank()) "작성된 기타 메모가 없습니다." else memo,
                    color = YeonTextOnBackGround,
                    fontFamily = fontNanumPen,
                    fontSize = 18.sp,
                    lineHeight = 24.sp
                )
            }
        }
    }
}