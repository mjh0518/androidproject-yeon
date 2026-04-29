package com.example.project_yeon.feature.person.detail

import com.example.project_yeon.core.common.result.ResultWrapper

data class DetailPersonUiState(
    val personId: Long = -1L,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val person: DetailPersonUiModel,
    val isSensitiveInfoUnlocked: Boolean = false,
    val isAuthInProgress: Boolean = false
)

data class DetailPersonUiModel(
    val id: Long,
    val profileImageUri: String? = null,
    val name: String,
    val intimacy: Int,
    val gender: String,
    val birthDate: String,
    val mbti: String,
    val personality: String,
    val personalityDescription: String? = "",
    val job: String? = "",
    val firstMetDate: String? = "",
    val firstMetPlace: String? = "",
    val firstMetMemory: String? = "",
    val likes: List<String> = emptyList(),
    val dislikes: List<String> = emptyList(),
    val traits: List<String> = emptyList(),
    val likesDescription: String? = "",
    val dislikesDescription: String? = "",
    val traitsDescription: String? = "",
    val lastContactDate: String? = "",
    val recentMetPlace: String? = "",
    val recentTalk: String? = "",
    val memo: String? = "",
    val memoryImageUris: List<String> = emptyList(),
    val phone: String? = "",
    val address: String? = "",
    val sns: String? = "",
)

sealed interface DetailPersonEvent {
    data object OnBackClick : DetailPersonEvent
    data object OnModifyClick : DetailPersonEvent
    data object OnRetryClick : DetailPersonEvent
    data object OnSensitiveInfoClick : DetailPersonEvent

    data class OnSensitiveAuthResult(
        val result: ResultWrapper<Unit>
    ) : DetailPersonEvent

    data class OnModifyAuthResult(
        val result: ResultWrapper<Unit>
    ) : DetailPersonEvent

}

sealed interface DetailPersonEffect {
    data object NavigateBack : DetailPersonEffect
    data class NavigateToModify(val personId: Long) : DetailPersonEffect
    data object RequestSensitiveAuth : DetailPersonEffect
    data object RequestModifyAuth : DetailPersonEffect
    data class ShowSnackbar(val message: String) : DetailPersonEffect
}