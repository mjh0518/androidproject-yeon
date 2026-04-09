package com.example.project_yeon.core.ui.component.dialog

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.project_yeon.core.ui.component.inputFfiled.YeonOutlinedTextField

@Composable
fun KeywordInputDialog(
    showDialog: Boolean,
    title: String,
    initialKeywords: List<String>,
    onConfirm: (List<String>) -> Unit,
    onDismiss: () -> Unit
) {
    if (!showDialog) return

    var inputValue by remember { mutableStateOf("") }
    var keywords by remember { mutableStateOf(emptyList<String>()) }

    LaunchedEffect(showDialog,) {
        if (showDialog) {
            inputValue = ""
            keywords = initialKeywords
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(title)
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    YeonOutlinedTextField(
                        value = inputValue,
                        onValueChange = { inputValue = it },
                        label = "",
                        modifier = Modifier.weight(1f)
                    )

                    TextButton(
                        onClick = {
                            val normalized = inputValue
                                .trim()
                                .removePrefix("#")
                            if (normalized.isNotBlank() && normalized !in keywords) {
                                keywords = keywords + normalized
                                inputValue = ""
                            }
                        }
                    ) {
                        Text("추가")
                    }
                    TextButton(
                        onClick = {
                           keywords = emptyList()
                        }
                    ) {
                        Text("비우기")
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column {
                        keywords.forEach { keyword ->
                            Text("#$keyword")
                        }
                    }
                    /*keywords.chunked(2).forEach { rowKeywords ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            rowKeywords.forEach { keyword ->
                                AssistChip(
                                    onClick = {},
                                    label = { Text("#$keyword") },
                                    trailingIcon = {
                                        IconButton(
                                            onClick = {
                                                keywords = keywords.filterNot { it == keyword }
                                            },
                                            modifier = Modifier.size(18.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Close,
                                                contentDescription = "키워드 삭제"
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }*/
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(keywords)
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