package com.example.project_yeon.feature.person.trash

import com.example.project_yeon.domain.person.model.HiddenPerson
import com.example.project_yeon.feature.person.trash.model.TrashPersonUiModel

data class TrashUiState(
    val isLoading: Boolean = true,
    val persons: List<TrashPersonUiModel> = emptyList(),
    val errorMessage: String? = null,
    val showPermanentDeleteDialog: Boolean = false,
    val selectedPersonId: Long? = null
)

sealed interface TrashEvent {
    data object OnBackClick : TrashEvent

    data class OnRestoreClick(val personId: Long) : TrashEvent
    data class OnPermanentDeleteClick(val personId: Long) : TrashEvent
    data object OnPermanentDeleteConfirmClick : TrashEvent
    data object OnDialogDismiss : TrashEvent
}

sealed interface TrashEffect {
    data object NavigateBack : TrashEffect
}