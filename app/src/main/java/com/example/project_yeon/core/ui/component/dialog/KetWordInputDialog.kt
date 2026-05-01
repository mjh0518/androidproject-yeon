package com.example.project_yeon.core.ui.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KeywordInputDialog(
    showDialog: Boolean,
    title: String,
    initialKeywords: List<String>,
    onConfirm: (List<String>) -> Unit,
    onDismiss: () -> Unit
) {
    if (!showDialog) return

    val fontNanumGyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))
    val fontNanumPen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))

    var inputValue by remember { mutableStateOf("") }
    var keywords by remember { mutableStateOf(emptyList<String>()) }

    LaunchedEffect(showDialog) {
        if (showDialog) {
            inputValue = ""
            keywords = initialKeywords
        }
    }

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
                    text = title,
                    color = YeonTextOnBackGround,
                    fontFamily = fontNanumGyuri,
                    fontSize = 30.sp
                )

                Text(
                    text = "기억하고 싶은 키워드를 하나씩 추가해주세요.",
                    color = YeonTextMuted,
                    fontFamily = fontNanumPen,
                    fontSize = 19.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextField(
                        value = inputValue,
                        onValueChange = { inputValue = it },
                        modifier = Modifier.weight(1f),
                        placeholder = {
                            Text(
                                text = "예: 커피, 산책",
                                color = YeonTextMuted.copy(alpha = 0.7f),
                                fontFamily = fontNanumPen,
                                fontSize = 20.sp
                            )
                        },
                        textStyle = LocalTextStyle.current.copy(
                            color = YeonTextOnBackGround,
                            fontFamily = fontNanumPen,
                            fontSize = 22.sp
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color(0xFFE4DACD),
                            unfocusedIndicatorColor = Color(0xFFE4DACD),
                            cursorColor = Color(0xFF8FA2FF)
                        )
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
                        Text(
                            text = "추가",
                            color = Color(0xFF8FA2FF),
                            fontFamily = fontNanumPen,
                            fontSize = 20.sp
                        )
                    }
                }

                if (keywords.isEmpty()) {
                    Text(
                        text = "아직 추가된 키워드가 없습니다.",
                        color = YeonTextMuted.copy(alpha = 0.75f),
                        fontFamily = fontNanumPen,
                        fontSize = 20.sp
                    )
                } else {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        keywords.forEach { keyword ->
                            AssistChip(
                                onClick = {},
                                label = {
                                    Text(
                                        text = "#$keyword",
                                        color = YeonTextOnBackGround,
                                        fontFamily = fontNanumPen,
                                        fontSize = 18.sp
                                    )
                                },
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            keywords = keywords - keyword
                                        },
                                        modifier = Modifier.size(22.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "키워드 삭제",
                                            modifier = Modifier.size(16.dp),
                                            tint = YeonTextMuted
                                        )
                                    }
                                },
                                shape = RoundedCornerShape(18.dp),
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = Color.White.copy(alpha = 0.62f),
                                    labelColor = YeonTextOnBackGround
                                ),
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = Color(0xFFD8CFC2).copy(alpha = 0.9f)
                                )
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {
                            keywords = emptyList()
                            inputValue = ""
                        }
                    ) {
                        Text(
                            text = "비우기",
                            color = YeonTextMuted,
                            fontFamily = fontNanumPen,
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

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

                    TextButton(
                        onClick = {
                            onConfirm(keywords)
                            onDismiss()
                        }
                    ) {
                        Text(
                            text = "확인",
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