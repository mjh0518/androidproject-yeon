package com.example.project_yeon.domain.person.model

data class HiddenPerson(
    val personId: Long,
    val deletedAt: Long,
    val expiryAt: Long,
    val meta: String
)