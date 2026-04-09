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

            is AddPersonEvent.CoreInfo.BirthDateChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        coreInfo = currentState.coreInfo.copy(
                            birthDate = event.value
                        ),
                        //isDirty = true
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

            is AddPersonEvent.PersonalityDescriptionChanged ->{
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            personalityDescription = event.value
                        ),
                        //isDirty = true
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

            is AddPersonEvent.LikesChanged ->{
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            likes = event.value
                        ),
                        //isDirty = true
                    )
                }
            }
            is AddPersonEvent.LikesDescriptionChanged ->{
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            likesDescription = event.value
                        ),
                        //isDirty = true
                    )
                }
            }

            is AddPersonEvent.DislikesChanged ->{
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            dislikes = event.value
                        ),
                        //isDirty = true
                    )
                }
            }
            is AddPersonEvent.DislikesDescriptionChanged ->{
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            dislikesDescription = event.value
                        ),
                        //isDirty = true
                    )
                }
            }

            is AddPersonEvent.TraitsChanged ->{
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            traits = event.value
                        ),
                        //isDirty = true
                    )
                }
            }
            is AddPersonEvent.TraitsDescriptionChanged ->{
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            traitsDescription = event.value
                        ),
                        //isDirty = true
                    )
                }
            }



            is AddPersonEvent.LastContactDateChanged ->{
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            lastContactDateText = event.value
                        ),
                        //isDirty = true
                    )
                }
            }

            is AddPersonEvent.LastRecentMetPlaceChanged ->{
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            recentMetPlace = event.value
                        ),
                        //isDirty = true
                    )
                }
            }

            is AddPersonEvent.MemorableConversationTalkChanged ->{
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            memorableConversationTalk = event.value
                        ),
                        //isDirty = true
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

            is AddPersonEvent.LivingAreaChanged ->{
                updateState { currentState ->
                    currentState.copy(
                        contactInfo = currentState.contactInfo.copy(
                            livingArea = event.value
                        ),
                        //isDirty = true
                    )
                }
            }

            is AddPersonEvent.PhoneNumberChanged ->{
                updateState { currentState ->
                    currentState.copy(
                        contactInfo = currentState.contactInfo.copy(
                            phoneNumber = event.value
                        ),
                        //isDirty = true
                    )
                }
            }

            is AddPersonEvent.ContactInfo.SnsLinkChanged -> {
                updateState { currentState ->
                    currentState.copy(
                        contactInfo = currentState.contactInfo.copy(
                            snsLink = event.value
                        ),
                        //isDirty = true
                    )
                }
            }

            is AddPersonEvent.JobChanged->{
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState.additionalInfo.copy(
                            job = event.value
                        ),
                        //isDirty = true
                    )
                }
            }

            is AddPersonEvent.MemoChanged ->{
                updateState { currentState ->
                    currentState.copy(
                        additionalInfo = currentState. additionalInfo.copy(
                            memo = event.value
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