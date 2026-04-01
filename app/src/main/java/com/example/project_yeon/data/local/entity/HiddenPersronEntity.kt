package com.example.project_yeon.data.local.entity

import androidx.room.*

@Entity(tableName = "hidden_person")
data class HiddenPersonEntity(
    @PrimaryKey
    val personId: Long,
    val deletedAt: Long,
    val expiryAt: Long,
    val meta: String
)