package com.example.project_yeon.feature.person.list

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround
import com.example.project_yeon.domain.person.model.PersonListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.project_yeon.R
import java.io.File

@Composable
fun HomeListDeleteModeContent(
    persons: List<PersonListItem>,
    selectedIds: Set<Long>,
    fontNanumPen: FontFamily,
    onCheckedChange: (Long) -> Unit,
    onDeleteClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        DeleteModeGuideText(
            fontNanumPen = fontNanumPen
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = persons,
                key = { it.personId }
            ) { person ->
                DeleteSelectablePersonCard(
                    person = person,
                    checked = selectedIds.contains(person.personId),
                    onCheckedChange = {
                        onCheckedChange(person.personId)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            DeleteSelectionSummaryText(
                selectedCount = selectedIds.size,
                fontNanumPen = fontNanumPen
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        DeleteModeBottomButtons(
            onDeleteClick = onDeleteClick,
            onBackClick = onBackClick,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
fun DeleteModeHeader(
    fontNanumPen: FontFamily,
    onDeleteIconClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "인연 삭제",
            fontFamily = fontNanumPen,
            fontSize = 30.sp,
            color = YeonTextOnBackGround
        )

        IconButton(
            onClick = onDeleteIconClick
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "삭제 실행",
                tint = YeonTextOnBackGround
            )
        }
    }
}
@Composable
fun DeleteModeGuideText(
    fontNanumPen: FontFamily
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "삭제할 인연을 선택해주세요.",
            fontFamily = fontNanumPen,
            fontSize = 18.sp,
            color = Color(0xFF7B8EDC)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "선택된 후 삭제되는 인연은 마지막 인연 보관함에서 복구한 뒤 보관됩니다.",
            fontFamily = fontNanumPen,
            fontSize = 12.sp,
            color = Color(0xFFE38C8C)
        )
    }
}
@Composable
fun DeleteSelectablePersonCard(
    person: PersonListItem,
    checked: Boolean,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    val font_nanum_gyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8F6F2)
        ),
        border = BorderStroke(1.2.dp, Color(0xFFD7CFC3)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(4.dp)
                    ),
            ) {
                AsyncImage(
                    model = if (person.profileImageUri.isNullOrBlank()) {
                        R.drawable.profile_default
                    } else {
                        File(person.profileImageUri)
                    },
                    contentDescription = "프로필 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    error = painterResource(R.drawable.profile_default),
                    fallback = painterResource(R.drawable.profile_default),
                    placeholder = painterResource(R.drawable.profile_default)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = person.name,
                modifier = Modifier.weight(1f),
                fontSize = 22.sp,
                fontFamily = font_nanum_gyuri,
                color = YeonTextOnBackGround
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    modifier = Modifier.size(26.dp),
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${person.intimacy}/5",
                    fontSize = 18.sp,
                    fontFamily = font_nanum_gyuri,
                    color = YeonTextOnBackGround
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Checkbox(
                checked = checked,
                onCheckedChange = { onCheckedChange() }
            )
        }
    }
}

@Composable
fun DeleteSelectionSummaryText(
    selectedCount: Int,
    fontNanumPen: FontFamily
) {
    Text(
        text = "삭제할 인연이 총 ${selectedCount}명 선택되어 있습니다.",
        fontFamily = fontNanumPen,
        fontSize = 16.sp,
        color = Color(0xFF7B8EDC)
    )
}

@Composable
fun DeleteModeBottomButtons(
    onDeleteClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedButton(
            onClick = onDeleteClick,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFFE59A9A))
        ) {
            Text(
                text = "삭제하기",
                color = Color(0xFFE59A9A)
            )
        }

        Button(
            onClick = onBackClick,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE8DFC5),
                contentColor = Color(0xFF7B8EDC)
            )
        ) {
            Text(text = "돌아가기")
        }
    }
}