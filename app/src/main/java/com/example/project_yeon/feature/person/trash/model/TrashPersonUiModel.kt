package com.example.project_yeon.feature.person.trash.model

data class TrashPersonUiModel(
    val personId: Long,
    val name: String,
    val profileImageUri: String?,
    val intimacy: Int,
    val deletedAt: Long,
    val expiryAt: Long
)