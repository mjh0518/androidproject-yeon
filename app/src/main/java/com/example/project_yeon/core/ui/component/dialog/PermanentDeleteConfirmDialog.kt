package com.example.project_yeon.core.ui.component.dialog

import com.example.project_yeon.core.ui.component.button.DialogTextAction

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

@Composable
fun PermanentDeleteConfirmDialog(
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
                    text = "정말 이 인연을 놓아주시겠습니까?",
                    fontFamily = fontNanumPen,
                    fontSize = 20.sp,
                    color = Color(0xFF7A736B)
                )

                Text(
                    text = "여기서 삭제한 인연은 추가가 불가능합니다.",
                    modifier = Modifier.padding(top = 18.dp),
                    fontFamily = fontNanumPen,
                    fontSize = 15.sp,
                    color = Color(0xFFE58A8A)
                )

                Text(
                    text = "소중했던 인연을 떠나보낼래요?",
                    modifier = Modifier.padding(top = 10.dp),
                    fontFamily = fontNanumPen,
                    fontSize = 17.sp,
                    color = Color(0xFFE58A8A)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 26.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DialogTextAction(
                        text = "삭제하기",
                        color = Color(0xFF3F3A34),
                        fontNanumPen = fontNanumPen,
                        onClick = onConfirm
                    )

                    DialogTextAction(
                        text = "취소하기",
                        color = Color(0xFF3F3A34),
                        fontNanumPen = fontNanumPen,
                        onClick = onDismiss
                    )
                }
            }
        }
    }
}