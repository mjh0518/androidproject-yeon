package com.example.project_yeon.feature.person.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun DetailSectionTitleRow(
    title: String,
    expanded: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val font_nanum_gyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))

    Row(
        modifier = modifier.fillMaxWidth().padding(start = 24.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            color = YeonTextOnBackGround,
            fontFamily = font_nanum_gyuri,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.width(24.dp))

        OutlinedButton(
            onClick = onToggle,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.45f)),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White.copy(alpha = 0.65f),
                contentColor = YeonTextOnBackGround
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text(
                text = if (expanded) "접기" else "펼치기",
                fontFamily = font_nanum_gyuri,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun DetailPreviewBox(
    text: String,
    modifier: Modifier = Modifier
) {
    val font_nanum_pen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White.copy(alpha = 0.55f),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f))
    ) {
        Text(
            text = if (text.isBlank()) "-" else text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            color = YeonTextOnBackGround,
            fontFamily = font_nanum_pen,
            fontSize = 18.sp,
            lineHeight = 24.sp
        )
    }
}

@Composable
fun DetailSimpleInfoText(
    label: String,
    value: String,
    labelFont: FontFamily,
    valueFont: FontFamily,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            color = YeonTextOnBackGround,
            fontFamily = labelFont,
            fontSize = 22.sp
        )

        Text(
            text = if (value.isBlank()) "-" else value,
            color = YeonTextOnBackGround,
            fontFamily = valueFont,
            fontSize = 20.sp
        )
    }
}

@Composable
fun ExpandableMemoryTextBlock(
    title: String,
    expanded: Boolean,
    onToggle: () -> Unit,
    text: String,
    titleFont: FontFamily,
    bodyFont: FontFamily,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MemorySectionTitleRow(
            title = title,
            expanded = expanded,
            onToggle = onToggle,
            fontFamily = titleFont
        )

        if (expanded) {
            MemoryContentBox {
                Text(
                    text = if (text.isBlank()) "기억에 남는 최근 대화가 없습니다." else text,
                    color = YeonTextOnBackGround,
                    fontFamily = bodyFont,
                    fontSize = 18.sp,
                    lineHeight = 24.sp
                )
            }
        }
    }
}

@Composable
fun ExpandableMemoryPhotoBlock(
    title: String,
    expanded: Boolean,
    onToggle: () -> Unit,
    imageUris: List<String>,
    titleFont: FontFamily,
    bodyFont: FontFamily,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MemorySectionTitleRow(
            title = title,
            expanded = expanded,
            onToggle = onToggle,
            fontFamily = titleFont
        )

        if (expanded) {
            MemoryContentBox {
                if (imageUris.isEmpty()) {
                    Text(
                        text = "등록된 사진이 없습니다.",
                        color = YeonTextOnBackGround,
                        fontFamily = bodyFont,
                        fontSize = 18.sp
                    )
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(horizontal = 2.dp)
                    ) {
                        items(imageUris) { imageUri ->
                            MemoryPhotoItem(imageUri = imageUri)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MemorySectionTitleRow(
    title: String,
    expanded: Boolean,
    onToggle: () -> Unit,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            color = YeonTextOnBackGround,
            fontFamily = fontFamily,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.width(12.dp))

        OutlinedButton(
            onClick = onToggle,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.45f)),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White.copy(alpha = 0.65f),
                contentColor = YeonTextOnBackGround
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text(
                text = if (expanded) "접기" else "펼치기",
                fontFamily = fontFamily,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun MemoryContentBox(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White.copy(alpha = 0.55f),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            content = content
        )
    }
}

@Composable
fun MemoryPhotoItem(
    imageUri: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.size(120.dp),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.45f)),
        color = Color.White.copy(alpha = 0.7f)
    ) {
        AsyncImage(
            model = imageUri,
            contentDescription = "함께한 사진",
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(14.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun SensitiveInfoBox(
    phone: String,
    address: String,
    sns: String,
    titleFont: FontFamily,
    bodyFont: FontFamily,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White.copy(alpha = 0.55f),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            SensitiveInfoLine(
                label = "전화번호",
                value = phone,
                labelFont = titleFont,
                valueFont = bodyFont
            )

            SensitiveInfoLine(
                label = "거주지",
                value = address,
                labelFont = titleFont,
                valueFont = bodyFont
            )

            SensitiveInfoLine(
                label = "SNS",
                value = sns,
                labelFont = titleFont,
                valueFont = bodyFont
            )
        }
    }
}

@Composable
fun SensitiveInfoLine(
    label: String,
    value: String,
    labelFont: FontFamily,
    valueFont: FontFamily,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            color = YeonTextOnBackGround,
            fontFamily = labelFont,
            fontSize = 20.sp
        )

        Text(
            text = if (value.isBlank()) "-" else value,
            color = YeonTextOnBackGround,
            fontFamily = valueFont,
            fontSize = 18.sp,
            lineHeight = 24.sp
        )
    }
}