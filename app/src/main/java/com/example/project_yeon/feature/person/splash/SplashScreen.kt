package com.example.project_yeon.feature.splash

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToHome: () -> Unit
) {
    val fontNanumGyuri = FontFamily(
        Font(R.font.nanumgyurieuilrgi, FontWeight.Normal)
    )

    val fontNanumPen = FontFamily(
        Font(R.font.nanumpen, FontWeight.Normal)
    )

    LaunchedEffect(Unit) {
        delay(1300L)
        onNavigateToHome()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.bg_paper_light),
                contentScale = ContentScale.Crop
            )
            .systemBarsPadding()
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(430.dp),
            shape = RoundedCornerShape(28.dp),
            color = Color(0xFFFFFCF5).copy(alpha = 0.94f),
            border = BorderStroke(
                width = 1.dp,
                color = Color(0xFFD8CFC2).copy(alpha = 0.8f)
            ),
            shadowElevation = 4.dp
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 28.dp, vertical = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "사람 중심 기억노트",
                        color = YeonTextMuted,
                        fontFamily = fontNanumGyuri,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Image(
                        painter = painterResource(id = R.drawable.ic_yeon_logo),
                        contentDescription = "연 앱 로고",
                        modifier = Modifier.size(170.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "기억하고 싶은 인연을,\n연에 담다.",
                        color = YeonTextOnBackGround,
                        fontFamily = fontNanumPen,
                        fontSize = 26.sp,
                        lineHeight = 34.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "소중한 사람의 흔적을\n천천히 기록하는 작은 서랍",
                        color = YeonTextMuted,
                        fontFamily = fontNanumGyuri,
                        fontSize = 17.sp,
                        lineHeight = 25.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun NoteLineBackground(
    modifier: Modifier = Modifier,
    lineColor: Color = Color(0xFFE4DACD).copy(alpha = 0.65f),
    startY: Float = 54f,
    lineGap: Float = 54f
) {
    Canvas(modifier = modifier) {
        var y = startY

        while (y < size.height) {
            drawLine(
                color = lineColor,
                start = Offset(x = 0f, y = y),
                end = Offset(x = size.width, y = y),
                strokeWidth = 1.2f,
                cap = StrokeCap.Round
            )
            y += lineGap
        }
    }
}