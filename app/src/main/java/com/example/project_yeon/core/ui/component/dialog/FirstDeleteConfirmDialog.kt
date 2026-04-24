package com.example.project_yeon.core.ui.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.project_yeon.core.ui.component.button.DialogTextAction

@Composable
fun FirstDeleteConfirmDialog(
    fontNanumPen: FontFamily,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color(0xFFD7CFC3)),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFDFBF6)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "선택한 항목들을 마지막 보관함으로 보낼까요?",
                    fontFamily = fontNanumPen,
                    fontSize = 20.sp,
                    color = Color(0xFFE58A8A)
                )

                Text(
                    text = "삭제한 인연은 마지막 인연보관함 속에서 영구간 보관됩니다.",
                    modifier = Modifier.padding(top = 18.dp),
                    fontFamily = fontNanumPen,
                    fontSize = 15.sp,
                    color = Color(0xFF6B6258)
                )

                Text(
                    text = "이 인연은 언제든지 다시 되돌릴 수 있어요.",
                    modifier = Modifier.padding(top = 6.dp),
                    fontFamily = fontNanumPen,
                    fontSize = 15.sp,
                    color = Color(0xFF6B6258)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 26.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DialogTextAction(
                        text = "보관함에 보내기",
                        color = Color(0xFFE58A8A),
                        fontNanumPen = fontNanumPen,
                        onClick = onConfirm
                    )

                    DialogTextAction(
                        text = "취소하기",
                        color = Color(0xFF7FA57A),
                        fontNanumPen = fontNanumPen,
                        onClick = onDismiss
                    )
                }
            }
        }
    }
}