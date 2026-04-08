package com.example.project_yeon.core.ui.component.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChoiceProfilePhotoDialog(
    showDialog: Boolean,
    onSelectDefaultImage: () -> Unit,
    onSelectGalleryImage: () -> Unit,
    onDismiss: () -> Unit
) {
    if (!showDialog) return

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("프로필 이미지 선택")
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onSelectGalleryImage()
                        onDismiss()
                    }
                ) {
                    Text("갤러리에서 사진 선택하기")
                }

                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onSelectDefaultImage()
                        onDismiss()
                    }
                ) {
                    Text("기본 이미지로 변경")
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("취소")
            }
        }
    )
}