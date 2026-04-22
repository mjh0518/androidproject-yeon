package com.example.project_yeon.domain.person.model

data class PersonListItem(
    val personId: Long,
    val name: String,
    val profileImageUri: String?,
    val intimacy: Int,
    val isPinned: Boolean,
    val pinnedAt : Long?,

    // 1차 상세 확장에 필요한 요약 정보
    val birthDateText: String?,
    val mbtiText: String?,
    val jobText: String?,
    val recentMeetPlaceText: String?,
    val genderText: String?,
    val personalityText: String?,
    val lastContactDateText: String?,
    val createdAt: Long,
)