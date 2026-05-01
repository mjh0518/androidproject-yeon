package com.example.project_yeon.core.ui.component.inputFfiled

import android.R.attr.text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

// 입력필드 중 확장이 없는 입력 필드
@Composable
fun YeonOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier.padding(top = 8.dp).padding(horizontal = 8.dp).height(60.dp),
){
    val fontNanumGyuri = FontFamily(
        Font(R.font.nanumgyurieuilrgi, FontWeight.Normal)
    )

    val fontNanumPen = FontFamily(
        Font(R.font.nanumpen, FontWeight.Normal)
    )

    TextField(
        value = value,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center,
            fontFamily = fontNanumPen,
            fontSize = 22.sp,
            color = YeonTextOnBackGround
        ),
        onValueChange = { newvalue -> onValueChange(newvalue) },
        label = { Text(label) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
        modifier = modifier,
    )
}