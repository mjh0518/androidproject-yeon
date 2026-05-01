package com.example.project_yeon.core.ui.component.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.example.project_yeon.R

@Composable
fun SnsLinkInputDialog(
    showDialog: Boolean,
    initialValue: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    if (!showDialog) return

    val fontNanumGyuri = FontFamily(
        Font(R.font.nanumgyurieuilrgi, FontWeight.Normal)
    )

    val fontNanumPen = FontFamily(
        Font(R.font.nanumpen, FontWeight.Normal)
    )

    YeonInputDialog(
        title = "대표 SNS 링크 입력",
        initialValue = initialValue,
        hint = "예: https://instagram.com/...",
        confirmText = "확인",
        dismissText = "취소",
        clearText = "비우기",
        keyboardType = KeyboardType.Uri,
        singleLine = true,
        titleFont = fontNanumGyuri,
        bodyFont = fontNanumPen,
        onDismiss = onDismiss,
        onConfirm = { value ->
            onConfirm(value.trim())
            onDismiss()
        }
    )
}