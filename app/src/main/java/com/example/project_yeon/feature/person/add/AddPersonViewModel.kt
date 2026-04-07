package com.example.project_yeon.feature.person.add

import androidx.lifecycle.ViewModel
import com.example.project_yeon.domain.person.repository.PersonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddPersonViewModel (
    //private val repo : PersonRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddPersonUiState())
    val uiState: StateFlow<AddPersonUiState> = _uiState.asStateFlow()

    fun onEvent(event: AddPersonEvent) {
        when (event) {
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
        }

    }

}