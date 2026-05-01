package com.example.project_yeon.core.ui.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun ChoiceProfilePhotoDialog(
    showDialog: Boolean,
    onSelectDefaultImage: () -> Unit,
    onSelectGalleryImage: () -> Unit,
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
                    text = "프로필 이미지 선택",
                    color = YeonTextOnBackGround,
                    fontFamily = fontNanumGyuri,
                    fontSize = 30.sp
                )

                Text(
                    text = "이 인연을 떠올릴 수 있는 사진을 골라주세요.",
                    color = YeonTextMuted,
                    fontFamily = fontNanumPen,
                    fontSize = 19.sp,
                    lineHeight = 26.sp
                )

                ProfilePhotoOptionRow(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = null,
                            tint = Color(0xFF8FA2FF),
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    text = "갤러리에서 사진 선택하기",
                    font = fontNanumPen,
                    onClick = {
                        onSelectGalleryImage()
                        onDismiss()
                    }
                )

                ProfilePhotoOptionRow(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = YeonTextMuted,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    text = "기본 이미지로 변경",
                    font = fontNanumPen,
                    onClick = {
                        onSelectDefaultImage()
                        onDismiss()
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(
                            text = "취소",
                            color = YeonTextMuted,
                            fontFamily = fontNanumPen,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfilePhotoOptionRow(
    icon: @Composable () -> Unit,
    text: String,
    font: FontFamily,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
        color = Color.White.copy(alpha = 0.62f),
        border = BorderStroke(
            width = 1.dp,
            color = Color(0xFFD8CFC2).copy(alpha = 0.85f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = text,
                color = YeonTextOnBackGround,
                fontFamily = font,
                fontSize = 21.sp,
                style = LocalTextStyle.current
            )
        }
    }
}