package com.example.project_yeon.feature.person.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun HomeListHeader(
    fontNanumPen: FontFamily,
    onNavigateToAdd: () -> Unit,
    onSearchClick: () -> Unit = {},
    onSortClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    onRecoveryClick: () -> Unit = {},
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "소중한 인연들",
            color = YeonTextOnBackGround,
            fontFamily = fontNanumPen,
            fontSize = 32.sp,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.2f),
                    offset = Offset(8f, 8f),
                    blurRadius = 2f
                )
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                HeaderActionIcon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "인연 추가",
                    onClick = onNavigateToAdd
                )
            }

            HeaderGroupDivider()

            Row(verticalAlignment = Alignment.CenterVertically) {
                HeaderActionIcon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "인연 검색",
                    onClick = onSearchClick
                )
                HeaderActionIcon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "인연 정렬",
                    onClick = onSortClick
                )

            }

            HeaderGroupDivider()

            Row(verticalAlignment = Alignment.CenterVertically) {
                HeaderActionIcon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "인연 삭제",
                    onClick = onDeleteClick
                )
                HeaderActionIcon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "인연 복원",
                    onClick = onRecoveryClick
                )
            }
        }
    }
}