package com.example.project_yeon.feature.person.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.project_yeon.R

@Composable
fun SearchResultSummary(
    query: String,
    resultCount: Int,
    modifier: Modifier = Modifier
) {
    val font_bmyeonsung = FontFamily(Font(R.font.bmyeonsung, FontWeight.Normal))

    if (query.isBlank()) return

    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            append("\"")
            withStyle(style = SpanStyle(color = Color(0xFF6B6258))) {
                append(query)
            }
            append("\" 검색 결과 ")
            if(resultCount == 0){
                append("일치하는 인연이 없습니다.")
            }
            else {
                withStyle(style = SpanStyle(color = Color(0xFF6B6258))) {
                    append("${resultCount}명")
                }
                append("이 일치합니다.")
            }
        },
        color = Color(0xFF6B6258),
        fontFamily = font_bmyeonsung,
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )
}