package com.example.project_yeon.core.ui.component.dialog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BirthDatePickerDialog(
    initialDate: LocalDate?,
    onDismiss: () -> Unit,
    onConfirm: (LocalDate) -> Unit
) {
    val fontNanumGyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))
    val fontNanumPen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))

    var input by remember(initialDate) {
        mutableStateOf(initialDate?.toRawDateText().orEmpty())
    }

    val parsedDate = remember(input) {
        input.toLocalDateOrNull()
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
                    text = "날짜 입력",
                    color = YeonTextOnBackGround,
                    fontFamily = fontNanumGyuri,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Start
                )

                Text(
                    text = "YYYYMMDD 형식으로 입력해주세요.",
                    color = YeonTextMuted,
                    fontFamily = fontNanumPen,
                    fontSize = 20.sp
                )

                YeonDateTextField(
                    value = input,
                    onValueChange = { value ->
                        input = value
                            .filter { it.isDigit() }
                            .take(8)
                    },
                    bodyFont = fontNanumPen
                )

                if (input.isNotBlank() && input.length == 8 && parsedDate == null) {
                    Text(
                        text = "올바른 날짜를 입력해주세요.",
                        color = Color(0xFFD45C5C),
                        fontFamily = fontNanumPen,
                        fontSize = 18.sp
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {
                            input = ""
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
                        enabled = parsedDate != null,
                        onClick = {
                            parsedDate?.let { date ->
                                onConfirm(date)
                            }
                        }
                    ) {
                        Text(
                            text = "확인",
                            color = if (parsedDate != null) {
                                Color(0xFF8FA2FF)
                            } else {
                                YeonTextMuted.copy(alpha = 0.45f)
                            },
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
private fun YeonDateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    bodyFont: FontFamily
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
    ) {
        Canvas(
            modifier = Modifier.matchParentSize()
        ) {
            val y = size.height - 12f

            drawLine(
                color = Color(0xFFE4DACD).copy(alpha = 0.85f),
                start = Offset(x = 0f, y = y),
                end = Offset(x = size.width, y = y),
                strokeWidth = 2f,
                cap = StrokeCap.Round
            )
        }

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart),
            placeholder = {
                Text(
                    text = "예: 20000101",
                    fontFamily = bodyFont,
                    color = YeonTextMuted.copy(alpha = 0.7f),
                    fontSize = 22.sp
                )
            },
            textStyle = LocalTextStyle.current.copy(
                fontFamily = bodyFont,
                color = YeonTextOnBackGround,
                fontSize = 24.sp
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            visualTransformation = DateVisualTransformation(),
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

private class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }.take(8)

        val transformed = buildString {
            digits.forEachIndexed { index, char ->
                append(char)

                if ((index == 3 || index == 5) && index != digits.lastIndex) {
                    append("/")
                }
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 4 -> offset
                    offset <= 6 -> offset + 1
                    else -> offset + 2
                }.coerceAtMost(transformed.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 4 -> offset
                    offset <= 7 -> offset - 1
                    else -> offset - 2
                }.coerceIn(0, digits.length)
            }
        }

        return TransformedText(
            text = AnnotatedString(transformed),
            offsetMapping = offsetMapping
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun LocalDate.toRawDateText(): String {
    val year = year.toString().padStart(4, '0')
    val month = monthValue.toString().padStart(2, '0')
    val day = dayOfMonth.toString().padStart(2, '0')

    return "$year$month$day"
}

@RequiresApi(Build.VERSION_CODES.O)
private fun String.toLocalDateOrNull(): LocalDate? {
    if (length != 8) return null

    return runCatching {
        val year = substring(0, 4).toInt()
        val month = substring(4, 6).toInt()
        val day = substring(6, 8).toInt()

        LocalDate.of(year, month, day)
    }.getOrNull()
}