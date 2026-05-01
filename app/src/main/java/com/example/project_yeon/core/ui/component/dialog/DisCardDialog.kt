package com.example.project_yeon.core.ui.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun DiscardDialog(
    showDialog: Boolean,
    onConfirmDiscard: () -> Unit,
    onDismiss: () -> Unit
) {
    if (!showDialog) return

    val fontNanumGyuri = FontFamily(
        Font(R.font.nanumgyurieuilrgi, FontWeight.Normal)
    )

    val fontNanumPen = FontFamily(
        Font(R.font.nanumpen, FontWeight.Normal)
    )

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape = RoundedCornerShape(28.dp),
            color = Color(0xFFFFFCF5).copy(alpha = 0.96f),
            border = BorderStroke(
                width = 1.dp,
                color = Color(0xFFD8CFC2).copy(alpha = 0.9f)
            ),
            shadowElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Text(
                    text = "입력 중인 내용이 있어요",
                    color = YeonTextOnBackGround,
                    fontFamily = fontNanumGyuri,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Start
                )

                Text(
                    text = "저장하지 않고 나가면\n작성한 내용이 사라질 수 있습니다.",
                    color = YeonTextMuted,
                    fontFamily = fontNanumPen,
                    fontSize = 21.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Start
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = {
                            onConfirmDiscard()
                        }
                    ) {
                        Text(
                            text = "나가기",
                            color = Color(0xFFD45C5C),
                            fontFamily = fontNanumPen,
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(
                            text = "계속 작성",
                            color = Color(0xFF8FA2FF),
                            fontFamily = fontNanumPen,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}