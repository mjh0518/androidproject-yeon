package com.example.project_yeon.feature.person.modify


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_yeon.core.common.result.ResultWrapper
import com.example.project_yeon.data.local.mapper.toModifyAdditionalInfoState
import com.example.project_yeon.data.local.mapper.toModifyContactInfoState
import com.example.project_yeon.data.local.mapper.toModifyCoreInfoState
import com.example.project_yeon.domain.person.usecase.GetPersonDetailUseCase
import com.example.project_yeon.domain.person.usecase.ModifyPersonUseCase
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
@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ModifyPersonViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPersonDetailUseCase: GetPersonDetailUseCase,
    private val modifyPersonUseCase: ModifyPersonUseCase,
    private val validatePersonDraftUseCase: ValidatePersonDraftUseCase,
) : ViewModel() {
    private val personId: Long = checkNotNull(savedStateHandle["personId"])

    private val _uiState = MutableStateFlow(ModifyPersonUiState())
    val uiState: StateFlow<ModifyPersonUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<ModifyPersonEffect>()
    val effect = _effect.asSharedFlow()

    init{
        loadPerson()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: ModifyPersonEvent) {
        when (event) {

            is ModifyPersonEvent.CoreInfo.ProfileImageChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        coreInfo = currentState.coreInfo.copy(
                            profileImageUri = event.image
                        ),
                    )
                }
            }

            is ModifyPersonEvent.NameChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            name = event.value
                        )
                    )
                }
            }

            is ModifyPersonEvent.GenderChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            gender = event.value
                        )
                    )
                }
            }

            is ModifyPersonEvent.CoreInfo.BirthDateChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        coreInfo = currentState.coreInfo.copy(
                            birthDate = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.IntimacyChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            intimacy = event.value
                        )
                    )
                }
            }

            is ModifyPersonEvent.MbtiChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            mbti = event.value
                        )
                    )
                }
            }

            is ModifyPersonEvent.PersonalityChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            personality = event.value
                        )
                    )
                }
            }

            is ModifyPersonEvent.PersonalityDescriptionChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            personalityDescription = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.FirstMetDateChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            firstMetDate = event.value
                        )
                    )
                }
            }

            is ModifyPersonEvent.FirstMetPlaceChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            firstMetPlace = event.value
                        )
                    )
                }
            }

            is ModifyPersonEvent.LikesChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            likes = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.LikesDescriptionChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            likesDescription = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.DislikesChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            dislikes = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.DislikesDescriptionChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            dislikesDescription = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.TraitsChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            traits = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.TraitsDescriptionChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            traitsDescription = event.value
                        ),
                    )
                }
            }


            is ModifyPersonEvent.LastContactDateChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            lastContactDateText = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.LastRecentMetPlaceChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            recentMetPlace = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.MemorableConversationTalkChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            memorableConversationTalk = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.AdditionalInfo.MemoryImagesAdded -> {
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

            is ModifyPersonEvent.AdditionalInfo.MemoryImageRemoved -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            memoryImageUris = currentState.additionalInfo.memoryImageUris
                                .filterNot { it == event.uri }
                        ),
                    )
                }
            }

            is ModifyPersonEvent.LivingAreaChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        contactInfo = currentState.contactInfo.copy(
                            livingArea = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.PhoneNumberChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        contactInfo = currentState.contactInfo.copy(
                            phoneNumber = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.ContactInfo.SnsLinkChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        contactInfo = currentState.contactInfo.copy(
                            snsLink = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.JobChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            job = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.MemoChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            memo = event.value
                        ),
                    )
                }
            }

            is ModifyPersonEvent.SaveClicked -> {
                _uiState.update { it.copy(isSaving = true) }
                val validateResult = validatePersonDraftUseCase(_uiState.value.toDraft())
                if (!validateResult.isValid) {
                    _uiState.update {
                        it.copy(
                            isSaving = false
                        )
                    }
                    sendEffect(ModifyPersonEffect.ShowSnackbar(validateResult.message ?: "필수 입력값을 확인해주세요."))
                    return
                } else {
                    val request = _uiState.value.toUpdateRequest()
                    viewModelScope.launch {
                        when (val result = modifyPersonUseCase(request)) {
                            is ResultWrapper.Success -> {
                                _uiState.update { it.copy(isSaving = false) }
                                sendEffect(ModifyPersonEffect.NavigateBack)
                            }
                            is ResultWrapper.Error -> {
                                _uiState.update { it.copy(isSaving = false) }
                                sendEffect(ModifyPersonEffect.ShowSnackbar("저장에 실패했습니다."))
                            }
                        }
                    }
                }

            }

            is ModifyPersonEvent.BackClicked -> {
                val currentState = _uiState.value

                if (currentState.isDirty) {
                    _uiState.value = currentState.copy(showDiscardDialog = true)
                } else {
                    sendEffect(ModifyPersonEffect.NavigateBack)
                }
            }

            is ModifyPersonEvent.ConfirmDiscardClicked -> {
                _uiState.value = _uiState.value.copy(
                    showDiscardDialog = false
                )
                sendEffect(ModifyPersonEffect.NavigateBack)
            }

            is ModifyPersonEvent.DiscardCancel -> {
                _uiState.value = _uiState.value.copy(showDiscardDialog = false)
            }
        }
    }

    private fun updateState(transform: (ModifyPersonUiState) -> ModifyPersonUiState) {
        _uiState.value = transform(_uiState.value)
    }

    private fun sendEffect(effect: ModifyPersonEffect) {
        viewModelScope.launch {
            Log.d("ModifyPersonViewModel", "sendEffect = $effect")
            _effect.emit(effect)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadPerson() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            try {
                val person = getPersonDetailUseCase(personId)

                val core = person.toModifyCoreInfoState()
                val additional = person.toModifyAdditionalInfoState()
                val contact = person.toModifyContactInfoState()

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = null,
                    personId = personId,
                    originalCoreInfo = core,
                    originalAdditionalInfo = additional,
                    originalContactInfo = contact,
                    coreInfo = core,
                    additionalInfo = additional,
                    contactInfo = contact
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "수정할 인연 정보를 불러오지 못했습니다."
                )
            }
        }
    }
}