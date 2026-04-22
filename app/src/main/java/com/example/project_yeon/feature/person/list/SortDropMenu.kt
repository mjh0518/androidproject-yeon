package com.example.project_yeon.feature.person.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_yeon.core.ui.theme.YeonSurface
import com.example.project_yeon.core.ui.theme.YeonSurfaceVariant
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun SortDropdownMenu(
    expanded: Boolean,
    currentSortType: SortType,
    fontNanumPen: FontFamily,
    onDismissRequest: () -> Unit,
    onSortTypeSelected: (SortType) -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        containerColor = Color.Transparent,
        shadowElevation = 0.dp,
        modifier = Modifier.width(140.dp)
    ) {
        Card(
            modifier = Modifier.width(140.dp).background(YeonSurfaceVariant),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, YeonTextMuted),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                SortType.entries.forEachIndexed { index, sortType ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(34.dp)
                            .clickable {
                                onSortTypeSelected(sortType)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = sortType.label,
                            fontFamily = fontNanumPen,
                            fontSize = 15.sp,
                            color = if (currentSortType == sortType) {
                                YeonTextOnBackGround
                            } else {
                                YeonTextOnBackGround.copy(alpha = 0.9f)
                            }
                        )
                    }

                    if (index != SortType.entries.lastIndex) {
                        HorizontalDivider(
                            thickness = 0.8.dp,
                            color = Color(0xFF8A847D)
                        )
                    }
                }
            }
        }
    }
}