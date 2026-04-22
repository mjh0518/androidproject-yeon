package com.example.project_yeon.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "person",
    indices = [
        Index(value = ["name"]),
        Index(value = ["createdAt"]),
        Index(value = ["pinned"]),
    ]
)
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    val personId: Long = 0,

    val name: String,                       // 이름
    val gender: String,                     // 성별
    val birthDate: String,                  // 생년월일
    val intimacy: Int,                      // 친밀도
    val mbti: String,                       // MBTI
    val personality: String,                // 성격
    val personalityDescription: String?,    // 성격 상세 설명

    val firstMetDate: String,               // 처음 만난 날
    val firstMetPlace: String,              // 처음 만난 곳

    val likes: String?,                     // 좋아하는 것 키워드 JSON
    val likesDescription: String?,          // 좋아하는 것 상세 설명
    val dislikes: String?,                  // 싫어하는 것 키워드 JSON
    val dislikesDescription: String?,       // 싫어하는 것 상세 설명
    val traits: String?,                    // 특징 키워드 JSON
    val traitsDescription: String?,         // 특징 상세 설명

    val lastContactDateText: String?,       // 마지막 연락 날짜
    val recentMetPlace: String?,            // 최근 만난 곳
    val memorableConversationTalk: String?, // 기억에 남는 최근 대화
    val memoryImageUris: String?,           // 함께한 사진들 JSON

    val profileImageUri: String?,           // 프로필 이미지 URI

    val livingArea: String?,                // 거주지
    val phoneNumber: String?,               // 전화번호
    val snsLink: String?,                   // 대표 SNS 링크
    val job: String?,                       // 직업
    val memo: String?,                      // 기타 메모

    val pinned: Boolean = false,
    val pinnedAt : Long ? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long
)