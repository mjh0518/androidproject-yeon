package com.example.project_yeon.domain.person.model

data class Person(
    val personId: Long,
    val name: String,
    val gender: String,
    val birthDate: String,
    val closeness: Int,
    val mbti: String,
    val personality: String,
    val personalityDetail: String?,
    val firstMetDay: String,
    val firstMetPlace: String,
    val likes: String?,
    val dislikes: String?,
    val characteristics: String?,
    val lastContactAt: String?,
    val lastMetPlace: String?,
    val recentConversation: String?,
    val photos: String?,
    val address: String?,
    val phone: String?,
    val sns: String?,
    val job: String?,
    val memo: String?,
    val pinned: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)