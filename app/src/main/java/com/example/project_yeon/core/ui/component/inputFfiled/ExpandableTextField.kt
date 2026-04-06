package com.example.project_yeon.core.ui.component.inputFfiled

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.project_yeon.core.ui.theme.YeonOutline
import com.example.project_yeon.core.ui.theme.YeonSurface


// 상세 입력란에 사용될 입력 텍스트란.

@Composable
fun ExpandableTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier.padding(top = 8.dp).padding(horizontal = 8.dp).height(60.dp),
    expandedMinHeight: Dp = 160.dp
) {
    var isFocused by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .width(600.dp)
            .height(if (isFocused || value.isNotBlank()) expandedMinHeight else 80.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(5.dp, YeonOutline),
        colors = CardDefaults.cardColors(
            containerColor = YeonSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        TextField(
            value = value, // 현재 텍스트 값
            onValueChange = { onValueChange }, // 텍스트 변경 시 상태 업데이트
            label = { Text(label) }, // 라벨 추가
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                // 필요에 따라 밑줄 색상도 투명하게 설정
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused },
        )
    }
}