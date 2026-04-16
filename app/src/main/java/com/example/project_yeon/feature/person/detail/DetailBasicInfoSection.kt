package com.example.project_yeon.feature.person.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun DetailBasicInfoSection(
    person: DetailPersonUiModel,
    modifier: Modifier = Modifier
) {

    var isPersonalityExpanded by rememberSaveable { mutableStateOf(false) }
    var isFirstMeetExpanded by rememberSaveable { mutableStateOf(false) }
    var isLikesExpanded by rememberSaveable { mutableStateOf(false) }
    var isDislikesExpanded by rememberSaveable { mutableStateOf(false) }
    var isTraitsExpanded by rememberSaveable { mutableStateOf(false) }

    val personalityPreview = if (person.personality.isBlank()) "-" else person.personality
    val personalityExpandedText = if (person.personalityDescription.isBlank()) {
        "성격에 대한 상세 설명이 없습니다."
    } else {
        person.personalityDescription
    }

    val firstMeetPreview = if (person.firstMetPlace.isBlank()) {
        "기록된 장소가 없습니다."
    } else {
        person.firstMetPlace
    }

    val firstMeetExpandedText = buildString {
        if (person.firstMetDate.isNotBlank()) {
            append("처음 만난 날짜 : ${person.firstMetDate}")
        }
        if (person.firstMetPlace.isNotBlank()) {
            if (isNotEmpty()) append("\n")
            append("처음 만난 곳 : ${person.firstMetPlace}")
        }
        if (person.firstMetMemory.isNotBlank()) {
            if (isNotEmpty()) append("\n\n")
            append(person.firstMetMemory)
        }
        if (isBlank()) {
            append("처음 만난 기록이 없습니다.")
        }
    }

    val likesPreview = remember(person.likes) {
        if (person.likes.isEmpty()) "키워드 없음"
        else person.likes.joinToString(", ")
    }

    val dislikesPreview = remember(person.dislikes) {
        if (person.dislikes.isEmpty()) "키워드 없음"
        else person.dislikes.joinToString(", ")
    }

    val traitsPreview = remember(person.traits) {
        if (person.traits.isEmpty()) "키워드 없음"
        else person.traits.joinToString(", ")
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        ExpandableInfoBlock(
            title = "성격 : $personalityPreview",
            previewText = personalityPreview,
            expandedText = personalityExpandedText,
            expanded = isPersonalityExpanded,
            onToggle = { isPersonalityExpanded = !isPersonalityExpanded },
        )

        ExpandableInfoBlock(
            title = "처음 만난 곳과 그 기억",
            previewText = firstMeetPreview,
            expandedText = firstMeetExpandedText,
            expanded = isFirstMeetExpanded,
            onToggle = { isFirstMeetExpanded = !isFirstMeetExpanded },
        )
         ExpandableInfoBlock(
            title = "이 사람이 좋아하는 것",
            previewText = likesPreview,
            expandedText = if (person.likesDescription.isBlank()) "상세 설명이 없습니다." else person.likesDescription, // 추후 likesDescription으로 교체 권장
            expanded = isLikesExpanded,
            onToggle = { isLikesExpanded = !isLikesExpanded },
        )

        ExpandableInfoBlock(
            title = "이 사람이 싫어하는 것",
            previewText = dislikesPreview,
            expandedText = if (person.dislikesDescription.isBlank()) "상세 설명이 없습니다." else person.dislikesDescription,
            expanded = isDislikesExpanded,
            onToggle = { isDislikesExpanded = !isDislikesExpanded },
        )

        ExpandableInfoBlock(
            title = "특징",
            previewText = traitsPreview,
            expandedText = if (person.traitsDescription.isBlank()) "상세 설명이 없습니다." else person.traitsDescription,
                    expanded = isTraitsExpanded,
            onToggle = { isTraitsExpanded = !isTraitsExpanded },
        )
    }
}