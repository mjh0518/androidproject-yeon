package com.example.project_yeon.feature.person.trash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_yeon.data.local.mapper.toTrashPersonUiModel
import com.example.project_yeon.domain.person.usecase.GetHiddenPersonsUseCase
import com.example.project_yeon.domain.person.usecase.PermanentlyDeleteFromTrashUseCase
import com.example.project_yeon.domain.person.usecase.RestorePersonFromTrashUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class TrashViewModel @Inject constructor(
    private val getHiddenPersonsUseCase: GetHiddenPersonsUseCase,
    private val restorePersonFromTrashUseCase: RestorePersonFromTrashUseCase,
    private val permanentlyDeleteFromTrashUseCase: PermanentlyDeleteFromTrashUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TrashUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<TrashEffect>()
    val effect = _effect.asSharedFlow()

    private var observeHiddenPersonsJob: Job? = null

    init {
        observeHiddenPersons()
    }

    private fun observeHiddenPersons() {
        observeHiddenPersonsJob?.cancel()
        observeHiddenPersonsJob = viewModelScope.launch {
            getHiddenPersonsUseCase()
                .catch { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message ?: "보관함 목록을 불러오지 못했습니다."
                        )
                    }
                }
                .collect { hiddenPersons ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            persons = hiddenPersons.map { hidden -> hidden.toTrashPersonUiModel() },
                            errorMessage = null
                        )
                    }
                }
        }
    }

    fun onEvent(event: TrashEvent) {
        when (event) {
            is TrashEvent.OnBackClick -> {
                viewModelScope.launch {
                    _effect.emit(TrashEffect.NavigateBack)
                }
            }

            is TrashEvent.OnRestoreClick -> {
                viewModelScope.launch {
                    restorePersonFromTrashUseCase(event.personId)
                }
            }

            is TrashEvent.OnPermanentDeleteClick -> {
                _uiState.update {
                    it.copy(
                        selectedPersonId = event.personId,
                        showPermanentDeleteDialog = true,
                    )
                }
            }

            is TrashEvent.OnPermanentDeleteConfirmClick -> {
                val personId = _uiState.value.selectedPersonId ?: return
                viewModelScope.launch {
                    permanentlyDeleteFromTrashUseCase(personId)
                    _uiState.update {
                        it.copy(
                            selectedPersonId = null,
                            showPermanentDeleteDialog = false
                        )
                    }
                }
            }

            is TrashEvent.OnDialogDismiss -> {
                _uiState.update {
                    it.copy(
                        selectedPersonId = null,
                        showPermanentDeleteDialog = false
                    )
                }
            }
        }
    }
}