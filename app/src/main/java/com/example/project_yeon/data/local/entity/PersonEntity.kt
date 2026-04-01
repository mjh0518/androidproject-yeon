package com.example.project_yeon.data.local.entity

import androidx.room.*

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

    val name : String, // 이름
    val gender : String, // 성별
    val birthDate : String, // 생년월일
    val closeness : Int, // 친밀도 (Rating)
    val mbti : String, // MBTI
    val personality : String, // 성격(드랍다운형)
    val personalityDetail : String?, //성격에 대한 상세 설명
    val firstMetDay : String, // 처음 만난 날
    val firstMetPlace : String, // 처음 만난 곳
    val likes: String?,     // 좋아하는 것 (키워드 + 상세설명) - JSON string
    val dislikes: String?,  // 싫어하는 것 (키워드 + 상세설명) - JSON string
    val characteristics : String?,  // 특징(키워드 + 상세 설명)JSON string
    val lastContactAt: String?, // 마지막 연락 날짜
    val lastMetPlace: String?, // 최근 만난 곳
    val recentConversation: String?, // 기억에 남는 최근 대화
    val photos: String?,    // 함께한 사진들 JSON string
    val address: String?,   // 거주지 , encrypted
    val phone: String?,     // 전화번호, encrypted
    val sns: String?,       // 개인 sns 링크 encrypted
    val job : String?,      // 직업
    val memo : String?,      // 기타 메모
    val pinned: Boolean = false,
    val createdAt: Long,
    val updatedAt: Long
)