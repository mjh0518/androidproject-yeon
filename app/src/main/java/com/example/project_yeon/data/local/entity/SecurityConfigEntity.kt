package com.example.project_yeon.data.local.entity

import androidx.room.*

@Entity(tableName = "security_config")
data class SecurityConfigEntity(
    @PrimaryKey val id: Int = 0,
    val pinHash: String?,
    val salt: String?,
    val biometricsEnabled: Boolean,
    val lastAuthAt: Long?
)
