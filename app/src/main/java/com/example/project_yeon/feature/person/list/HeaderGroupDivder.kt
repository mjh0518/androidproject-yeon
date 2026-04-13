package com.example.project_yeon.feature.person.list

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun HeaderGroupDivider() {
    VerticalDivider(
        modifier = Modifier
            .height(18.dp)
            .padding(horizontal = 2.dp),
        thickness = 1.dp,
        color = YeonTextOnBackGround.copy(alpha = 0.25f)
    )
}