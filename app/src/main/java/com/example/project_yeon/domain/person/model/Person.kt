package com.example.project_yeon.domain.person.model

data class Person(
    val personId: Long,
    val name: String,
    val gender: String,
    val birthDate: String,
    val intimacy: Int,
    val mbti: String,
    val personality: String,
    val personalityDescription: String?,

    val firstMetDate: String,
    val firstMetPlace: String,

    val likes: List<String>,
    val likesDescription: String?,
    val dislikes: List<String>,
    val dislikesDescription: String?,
    val traits: List<String>,
    val traitsDescription: String?,

    val lastContactDateText: String?,
    val recentMetPlace: String?,
    val memorableConversationTalk: String?,
    val memoryImageUris: List<String>,

    val profileImageUri: String?,

    val livingArea: String?,
    val phoneNumber: String?,
    val snsLink: String?,
    val job: String?,
    val memo: String?,

    val pinned: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)