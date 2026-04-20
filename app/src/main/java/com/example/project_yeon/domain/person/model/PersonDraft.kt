package com.example.project_yeon.domain.person.model

import com.example.project_yeon.domain.person.model.common.Gender
import java.time.LocalDate

data class PersonDraft(
    val name: String,
    val gender: Gender?,
    val birthDate: LocalDate?,
    val intimacy: Int,
    val mbti: String,
    val personality: String,
    val firstMetDate: String,
    val firstMetPlace: String
)