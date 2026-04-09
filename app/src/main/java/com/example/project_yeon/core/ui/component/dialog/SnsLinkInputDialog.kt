package com.example.project_yeon.core.ui.component.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.project_yeon.core.ui.component.inputFfiled.YeonOutlinedTextField

@Composable
fun SnsLinkInputDialog(
    showDialog: Boolean,
    initialValue: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    if (!showDialog) return

    var inputValue by remember { mutableStateOf(initialValue) }

    LaunchedEffect(initialValue, showDialog) {
        if (showDialog) {
            inputValue = initialValue
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("대표 SNS 링크 입력")
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                YeonOutlinedTextField(
                    value = inputValue,
                    onValueChange = { inputValue = it },
                    label = "",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(inputValue.trim())
                    onDismiss()
                }
            ) {
                Text("확인")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("취소")
            }
        }
    )
}