package com.example.project_yeon.core.ui.component.inputFfiled

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonOutline
import com.example.project_yeon.core.ui.theme.YeonSurface
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun ActionFieldContainer(
    text: String,
    placeholder: String,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val fontNanumPen = FontFamily(
        Font(R.font.nanumpen, FontWeight.Normal)
    )

    val isPlaceholder = text.isBlank()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(5.dp, YeonOutline),
        colors = CardDefaults.cardColors(
            containerColor = YeonSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = onClick,
        enabled = enabled
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isPlaceholder) placeholder else text,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontFamily = fontNanumPen,
                        fontSize = 22.sp,
                        color = if (isPlaceholder) {
                            YeonTextMuted.copy(alpha = 0.75f)
                        } else {
                            YeonTextOnBackGround
                        }
                    )
                )
            }

            Box(
                modifier = Modifier.width(56.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                icon()
            }
        }
    }
}