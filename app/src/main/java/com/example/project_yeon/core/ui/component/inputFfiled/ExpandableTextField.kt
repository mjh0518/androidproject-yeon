package com.example.project_yeon.core.ui.component.inputFfiled

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonOutline
import com.example.project_yeon.core.ui.theme.YeonSurface
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun ExpandableTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier
        .padding(top = 8.dp)
        .padding(horizontal = 8.dp),
    collapsedHeight: Dp = 60.dp,
    expandedHeight: Dp = 160.dp
) {
    var isFocused by remember { mutableStateOf(false) }

    val fontNanumGyuri = FontFamily(
        Font(R.font.nanumgyurieuilrgi, FontWeight.Normal)
    )

    val fontNanumPen = FontFamily(
        Font(R.font.nanumpen, FontWeight.Normal)
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(if (isFocused || value.isNotBlank()) expandedHeight else collapsedHeight),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(5.dp, YeonOutline),
        colors = CardDefaults.cardColors(
            containerColor = YeonSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = label,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = fontNanumGyuri,
                    fontSize = 18.sp,
                    color = YeonTextMuted
                )
            },
            placeholder = {
                Text(
                    text = placeholder,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = fontNanumPen,
                    fontSize = 20.sp,
                    color = YeonTextMuted.copy(alpha = 0.75f)
                )
            },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                fontFamily = fontNanumPen,
                fontSize = 22.sp,
                color = YeonTextOnBackGround
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0xFF8FA2FF)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
        )
    }
}