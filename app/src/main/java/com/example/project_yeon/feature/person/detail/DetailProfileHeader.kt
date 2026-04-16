package com.example.project_yeon.feature.person.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun DetailProfileHeader(
    person: DetailPersonUiModel,
    onModifyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fontNanumPen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            ProfileImageBox(
                profileImageUri = person.profileImageUri
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DetailHeaderInfoText(
                    label = "이름",
                    value = person.name,
                    fontFamily = fontNanumPen
                )
                DetailHeaderInfoText(
                    label = "성별",
                    value = person.gender,
                    fontFamily = fontNanumPen
                )
                DetailHeaderInfoText(
                    label = "생년월일",
                    value = person.birthDate,
                    fontFamily = fontNanumPen
                )
                DetailHeaderInfoText(
                    label = "MBTI",
                    value = person.mbti,
                    fontFamily = fontNanumPen
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            IntimacyBadge(
                intimacy = person.intimacy,
                fontFamily = fontNanumPen
            )
        }
    }
}

@Composable
private fun ProfileImageBox(
    profileImageUri: String?,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .size(width = 92.dp, height = 92.dp),
        shape = RoundedCornerShape(14.dp),
        color = Color.Transparent,
        border = BorderStroke(2.dp, Color.Black.copy(alpha = 0.75f))
    ) {
        if (profileImageUri.isNullOrBlank()) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.AccountBox,
                    contentDescription = "프로필 이미지 없음",
                    modifier = Modifier.size(42.dp),
                    tint = Color.Black.copy(alpha = 0.7f)
                )
            }
        } else {
            AsyncImage(
                model = profileImageUri,
                contentDescription = "프로필 이미지",
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun DetailHeaderInfoText(
    label: String,
    value: String,
    fontFamily: FontFamily
) {
    Text(
        text = "$label :  ${if (value.isBlank()) "-" else value}",
        color = YeonTextOnBackGround,
        fontFamily = fontFamily,
        fontSize = 20.sp,
        lineHeight = 24.sp
    )
}

@Composable
private fun IntimacyBadge(
    intimacy: Int,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "친밀도",
            tint = Color(0xFFFFE100),
            modifier = Modifier.size(34.dp)
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "${intimacy}/5",
            color = YeonTextOnBackGround,
            fontFamily = fontFamily,
            fontSize = 18.sp
        )
    }
}