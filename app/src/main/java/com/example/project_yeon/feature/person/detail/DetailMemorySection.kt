package com.example.project_yeon.feature.person.detail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun DetailMemorySection(
    person: DetailPersonUiModel,
    modifier: Modifier = Modifier
) {
    val fontNanumGyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))
    val fontNanumPen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))

    var isRecentTalkExpanded by rememberSaveable { mutableStateOf(false) }
    var isMemoryPhotosExpanded by rememberSaveable { mutableStateOf(false) }

    val lastContactDate = person.lastContactDate?.trim().orEmpty()
    val recentTalk = person.recentTalk?.trim().orEmpty()

    Log.d("DetailPerson", "memoryImageUris size = ${person.memoryImageUris.size}")
    person.memoryImageUris.forEachIndexed { index, uri ->
        Log.d("DetailPerson", "memoryImageUris[$index] = $uri")
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DetailSimpleInfoText(
            label = "마지막 연락날",
            value = lastContactDate,
            labelFont = fontNanumGyuri,
            valueFont = fontNanumPen
        )

        ExpandableMemoryTextBlock(
            title = "기억에 남는 최근 대화",
            expanded = isRecentTalkExpanded,
            onToggle = { isRecentTalkExpanded = !isRecentTalkExpanded },
            text = recentTalk,
            titleFont = fontNanumGyuri,
            bodyFont = fontNanumPen
        )

        ExpandableMemoryPhotoBlock(
            title = "이 사람과 함께한 사진",
            expanded = isMemoryPhotosExpanded,
            onToggle = { isMemoryPhotosExpanded = !isMemoryPhotosExpanded },
            imageUris = person.memoryImageUris,
            titleFont = fontNanumGyuri,
            bodyFont = fontNanumPen
        )
    }
}