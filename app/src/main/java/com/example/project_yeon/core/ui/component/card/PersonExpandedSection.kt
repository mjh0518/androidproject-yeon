package com.example.project_yeon.core.ui.component.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_yeon.R
import com.example.project_yeon.domain.person.model.PersonListItem
import com.example.project_yeon.core.ui.theme.YeonOutline
import com.example.project_yeon.core.ui.theme.YeonSurface
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun PersonExpandedSection(
    person: PersonListItem,
    onMoreDetailClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fontNanumPen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))
    val fontNanumGyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
        border = BorderStroke(2.dp, YeonOutline),
        colors = CardDefaults.cardColors(
            containerColor = YeonSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    ExpandedInfoRow(
                        label = "생년월일 : ",
                        value = person.birthDateText ?: "-"
                    )
                    ExpandedInfoRow(
                        label = "MBTI : ",
                        value = person.mbtiText ?: "-"
                    )
                    ExpandedInfoRow(
                        label = "직업 : ",
                        value = person.jobText ?: "-"
                    )
                    ExpandedInfoRow(
                        label = "최근 만난 곳 : ",
                        value = person.recentMeetPlaceText ?: "-"
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .width(1.dp)
                        .height(92.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    HorizontalDivider(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(92.dp),
                        thickness = 1.dp,
                        color = Color.Transparent
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    ExpandedInfoRow(
                        label = "성별 : ",
                        value = when(person.genderText){"MALE" ->{"남"} "FEMALE"->{"여"} else -> ""}
                    )
                    ExpandedInfoRow(
                        label = "성격 : ",
                        value = person.personalityText ?: "-"
                    )
                    ExpandedInfoRow(
                        label = "마지막 연락일 : ",
                        value = person.lastContactDateText ?: "-"
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = YeonOutline.copy(alpha = 0.45f)
            )

            TextButton(
                onClick = onMoreDetailClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AccountBox,
                        contentDescription = "더 많은 정보 보기",
                        tint = YeonTextOnBackGround
                    )
                    Text(
                        text = " 더 많은 정보 보기",
                        color = YeonTextOnBackGround,
                        fontFamily = fontNanumPen,
                        fontSize = 24.sp,
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black.copy(alpha = 0.08f),
                                offset = Offset(1f, 1f),
                                blurRadius = 1f
                            )
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ExpandedInfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    val fontNanumPen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))
    val fontNanumGyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                color = YeonTextOnBackGround,
                fontFamily = fontNanumPen,
                fontSize = 18.sp
            )
            Text(
                text = value,
                color = YeonTextMuted,
                fontFamily = fontNanumGyuri,
                fontSize = 18.sp
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, end = 8.dp),
            thickness = 1.dp,
            color = YeonOutline.copy(alpha = 0.25f)
        )
    }
}