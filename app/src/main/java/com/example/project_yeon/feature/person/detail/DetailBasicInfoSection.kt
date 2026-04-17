package com.example.project_yeon.feature.person.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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

    val personalityPreview = person.personality.takeIf { it.isNotBlank() } ?: "-"
    val personalityExpandedText =
        person.personalityDescription?.takeIf { it.isNotBlank() }
            ?: "성격에 대한 상세 설명이 없습니다."

    val firstMeetPlace = person.firstMetPlace?.trim().orEmpty()
    val firstMeetDate = person.firstMetDate?.trim().orEmpty()
    val firstMeetMemory = person.firstMetMemory?.trim().orEmpty()

    val firstMeetPreview = if (firstMeetPlace.isBlank()) {
        "기록된 장소가 없습니다."
    } else {
        "펼쳐서 확인 해보기"
    }

    val firstMeetExpandedText = buildString {
        if (firstMeetDate.isNotBlank()) {
            append("처음 만난 날짜 : $firstMeetDate")
        }
        if (firstMeetPlace.isNotBlank()) {
            if (isNotEmpty()) append("\n")
            append("처음 만난 곳 : $firstMeetPlace")
        }
        if (firstMeetMemory.isNotBlank()) {
            if (isNotEmpty()) append("\n\n")
            append(firstMeetMemory)
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

    val likesExpandedText =
        person.likesDescription?.takeIf { it.isNotBlank() }
            ?: "상세 설명이 없습니다."

    val dislikesExpandedText =
        person.dislikesDescription?.takeIf { it.isNotBlank() }
            ?: "상세 설명이 없습니다."

    val traitsExpandedText =
        person.traitsDescription?.takeIf { it.isNotBlank() }
            ?: "상세 설명이 없습니다."

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        ExpandableInfoBlock(
            title = "성격",
            previewText = personalityPreview,
            expandedText = personalityExpandedText,
            expanded = isPersonalityExpanded,
            onToggle = { isPersonalityExpanded = !isPersonalityExpanded }
        )

        ExpandableInfoBlock(
            title = "처음 만난 곳과 그 기억",
            previewText = firstMeetPreview,
            expandedText = firstMeetExpandedText,
            expanded = isFirstMeetExpanded,
            onToggle = { isFirstMeetExpanded = !isFirstMeetExpanded }
        )

        ExpandableInfoBlock(
            title = "이 사람이 좋아하는 것",
            previewText = likesPreview,
            expandedText = likesExpandedText,
            expanded = isLikesExpanded,
            onToggle = { isLikesExpanded = !isLikesExpanded }
        )

        ExpandableInfoBlock(
            title = "이 사람이 싫어하는 것",
            previewText = dislikesPreview,
            expandedText = dislikesExpandedText,
            expanded = isDislikesExpanded,
            onToggle = { isDislikesExpanded = !isDislikesExpanded }
        )

        ExpandableInfoBlock(
            title = "특징",
            previewText = traitsPreview,
            expandedText = traitsExpandedText,
            expanded = isTraitsExpanded,
            onToggle = { isTraitsExpanded = !isTraitsExpanded }
        )
    }
}