package com.example.project_yeon.core.ui.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun YeonInputDialog(
    title: String,
    initialValue: String = "",
    hint: String = "",
    confirmText: String = "확인",
    dismissText: String = "취소",
    clearText: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    titleFont: FontFamily,
    bodyFont: FontFamily,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var input by remember(initialValue) {
        mutableStateOf(initialValue)
    }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = modifier
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
                    fontFamily = titleFont,
                    fontSize = androidx.compose.ui.unit.TextUnit.Unspecified,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Start
                )

                YeonNotebookTextField(
                    value = input,
                    onValueChange = { input = it },
                    hint = hint,
                    bodyFont = bodyFont,
                    keyboardType = keyboardType,
                    singleLine = singleLine
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (clearText != null) {
                        TextButton(
                            onClick = { input = "" }
                        ) {
                            Text(
                                text = clearText,
                                fontFamily = bodyFont,
                                color = YeonTextMuted
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(
                            text = dismissText,
                            fontFamily = bodyFont,
                            color = YeonTextMuted
                        )
                    }

                    TextButton(
                        onClick = {
                            onConfirm(input.trim())
                        }
                    ) {
                        Text(
                            text = confirmText,
                            fontFamily = bodyFont,
                            color = Color(0xFF8FA2FF)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun YeonNotebookTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    bodyFont: FontFamily,
    keyboardType: KeyboardType,
    singleLine: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(if (singleLine) 72.dp else 120.dp)
    ) {
        NotebookLineBackground(
            modifier = Modifier.matchParentSize()
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart),
            placeholder = {
                Text(
                    text = hint,
                    fontFamily = bodyFont,
                    color = YeonTextMuted.copy(alpha = 0.7f)
                )
            },
            textStyle = LocalTextStyle.current.copy(
                fontFamily = bodyFont,
                color = YeonTextOnBackGround
            ),
            singleLine = singleLine,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0xFF8FA2FF)
            )
        )
    }
}

@Composable
private fun NotebookLineBackground(
    modifier: Modifier = Modifier,
    lineColor: Color = Color(0xFFE4DACD).copy(alpha = 0.8f)
) {
    Canvas(modifier = modifier) {
        val y = size.height - 12f

        drawLine(
            color = lineColor,
            start = Offset(x = 0f, y = y),
            end = Offset(x = size.width, y = y),
            strokeWidth = 2f,
            cap = StrokeCap.Round
        )
    }
}