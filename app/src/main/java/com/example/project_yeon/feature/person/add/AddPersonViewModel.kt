package com.example.project_yeon.feature.person.add

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_yeon.core.common.result.ResultWrapper
import com.example.project_yeon.domain.person.usecase.CreatePersonUseCase
import com.example.project_yeon.domain.person.usecase.ValidatePersonDraftUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
@HiltViewModel
class AddPersonViewModel @Inject constructor(
    private val createPersonUseCase: CreatePersonUseCase,
    private val validatePersonDraftUseCase: ValidatePersonDraftUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddPersonUiState())
    val uiState: StateFlow<AddPersonUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<AddPersonEffect>()
    val effect = _effect.asSharedFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: AddPersonEvent) {
        when (event) {

            is AddPersonEvent.CoreInfo.ProfileImageChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        coreInfo = currentState.coreInfo.copy(
                            profileImageUri = event.image
                        ),
                    )
                }
            }

            is AddPersonEvent.NameChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            name = event.value
                        )
                    )
                }
            }

            is AddPersonEvent.GenderChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            gender = event.value
                        )
                    )
                }
            }

            is AddPersonEvent.CoreInfo.BirthDateChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        coreInfo = currentState.coreInfo.copy(
                            birthDate = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.IntimacyChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            intimacy = event.value
                        )
                    )
                }
            }

            is AddPersonEvent.MbtiChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            mbti = event.value
                        )
                    )
                }
            }

            is AddPersonEvent.PersonalityChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            personality = event.value
                        )
                    )
                }
            }

            is AddPersonEvent.PersonalityDescriptionChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            personalityDescription = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.FirstMetDateChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            firstMetDate = event.value
                        )
                    )
                }
            }

            is AddPersonEvent.FirstMetPlaceChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            firstMetPlace = event.value
                        )
                    )
                }
            }

            is AddPersonEvent.LikesChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            likes = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.LikesDescriptionChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            likesDescription = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.DislikesChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            dislikes = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.DislikesDescriptionChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            dislikesDescription = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.TraitsChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            traits = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.TraitsDescriptionChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            traitsDescription = event.value
                        ),
                    )
                }
            }


            is AddPersonEvent.LastContactDateChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            lastContactDateText = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.LastRecentMetPlaceChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            recentMetPlace = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.MemorableConversationTalkChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            memorableConversationTalk = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.AdditionalInfo.MemoryImagesAdded -> {
                updateState { currentState ->
                    val merged = (
                            currentState.additionalInfo.memoryImageUris + event.uris
                            ).distinct()

                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            memoryImageUris = merged
                        ),
                    )
                }
            }

            is AddPersonEvent.AdditionalInfo.MemoryImageRemoved -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            memoryImageUris = currentState.additionalInfo.memoryImageUris
                                .filterNot { it == event.uri }
                        ),
                    )
                }
            }

            is AddPersonEvent.LivingAreaChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        contactInfo = currentState.contactInfo.copy(
                            livingArea = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.PhoneNumberChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        contactInfo = currentState.contactInfo.copy(
                            phoneNumber = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.ContactInfo.SnsLinkChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        contactInfo = currentState.contactInfo.copy(
                            snsLink = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.JobChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            job = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.MemoChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            memo = event.value
                        ),
                    )
                }
            }

            is AddPersonEvent.SaveClicked -> {
                val validateResult = validatePersonDraftUseCase(_uiState.value)
                if (!validateResult.isValid) {
                    viewModelScope.launch {
                        sendEffect(AddPersonEffect.ShowSnackbar(
                            validateResult.message ?: "필수 입력값을 확인해주세요."))
                    }
                    return
                } else {
                    val request = _uiState.value.toCreateRequest()
                    viewModelScope.launch {
                        when (val result = createPersonUseCase(request)) {
                            is ResultWrapper.Success -> {
                                sendEffect(AddPersonEffect.NavigateBack)
                            }
                            is ResultWrapper.Error -> {
                                sendEffect(AddPersonEffect.ShowSnackbar("저장에 실패했습니다."))
                            }
                        }
                    }
                }

            }

            is AddPersonEvent.BackClicked -> {
                val currentState = _uiState.value

                if (currentState.isDirty) {
                    _uiState.value = currentState.copy(showDiscardDialog = true)
                } else {
                    sendEffect(AddPersonEffect.NavigateBack)
                }
            }

            is AddPersonEvent.ConfirmDiscardClicked -> {
                _uiState.value = _uiState.value.copy(
                    showDiscardDialog = false
                )
                sendEffect(AddPersonEffect.NavigateBack)
            }

            is AddPersonEvent.DiscardCancel -> {
                _uiState.value = _uiState.value.copy(showDiscardDialog = false)
            }
        }
    }

    private fun updateState(transform: (AddPersonUiState) -> AddPersonUiState) {
        _uiState.value = transform(_uiState.value)
    }

    private fun sendEffect(effect: AddPersonEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}