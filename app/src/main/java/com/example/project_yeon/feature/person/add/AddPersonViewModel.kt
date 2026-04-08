package com.example.project_yeon.feature.person.add

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.project_yeon.domain.person.repository.PersonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.nio.file.Files.copy

class AddPersonViewModel(
    //private val repo : PersonRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddPersonUiState())
    val uiState: StateFlow<AddPersonUiState> = _uiState.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: AddPersonEvent) {
        when (event) {

            is AddPersonEvent.CoreInfo.ProfileImageChanged ->{
                updateState { currentState ->
                    currentState.copy(
                        coreInfo = currentState.coreInfo.copy(
                            profileImageUri = event.image
                        ),
                        //isDirty = true
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

            is AddPersonEvent.BirthDateChanged -> {
                _uiState.update {
                    it.copy(
                        coreInfo = it.coreInfo.copy(
                            birthDate = event.value
                        )
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

            is AddPersonEvent.AdditionalInfo.MemoryImagesAdded -> {
                updateState { currentState ->
                    val merged = (
                            currentState.additionalInfo.memoryImageUris + event.uris
                            ).distinct()

                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            memoryImageUris = merged
                        ),
                        //isDirty = true
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
                        //isDirty = true
                    )
                }
            }


            is AddPersonEvent.SaveClicked -> {
                val core = _uiState.value.coreInfo

                val isValid =
                    core.name.isNotBlank() &&
                            core.gender != null &&
                            core.birthDate != null &&
                            core.mbti.isNotBlank() &&
                            core.personality.isNotBlank() &&
                            core.firstMetDate != null &&
                            core.firstMetPlace.isNotBlank()

                if (isValid) {
                    Log.d("AddPersonViewModel", "Save validation success")
                } else {
                    Log.d("AddPersonViewModel", "Save validation fail")
                }

            }
        }
    }
    private fun updateState(transform: (AddPersonUiState) -> AddPersonUiState) {
        _uiState.value = transform(_uiState.value)
    }
}