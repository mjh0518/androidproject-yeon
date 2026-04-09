package com.example.project_yeon.core.ui.component.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DiscardDialog(
    showDialog: Boolean,
    onConfirmDiscard: () -> Unit,
    onDismiss: () -> Unit
) {
    if (!showDialog) return

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("입력 중인 내용이 있어요")
        },
        text = {
            Text("저장하지 않고 나가면 작성한 내용이 사라질 수 있습니다.")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmDiscard()
                }
            ) {
                Text("나가기")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("계속 작성")
            }
        }
    )
}