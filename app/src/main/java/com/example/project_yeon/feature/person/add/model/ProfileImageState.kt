package com.example.project_yeon.feature.person.add.model

sealed interface ProfileImageState {
    data object Default : ProfileImageState
    data class Custom(val uri: String) : ProfileImageState
}