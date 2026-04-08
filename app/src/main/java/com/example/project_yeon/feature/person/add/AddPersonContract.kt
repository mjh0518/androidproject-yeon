package com.example.project_yeon.feature.person.add

import com.example.project_yeon.feature.person.add.model.ProfileImageState
import kotlinx.serialization.descriptors.SerialDescriptor
import java.time.LocalDate
/*
data class AddPersonUiState(
    val coreInfo: AddPersonCoreInfoState = AddPersonCoreInfoState(),
    val relationInfo: AddPersonRelationInfoState = AddPersonRelationInfoState(),
    val additionalInfo: AddPersonAdditionalInfoState = AddPersonAdditionalInfoState(),
    val contactInfo: AddPersonContactInfoState = AddPersonContactInfoState(),
    val fieldErrors: Map<AddPersonField, String> = emptyMap(),
    val isDirty: Boolean = false,
    val isSaving: Boolean = false
)*/

enum class Gender {
    MALE, FEMALE
}

data class AddPersonCoreInfoState(
    val profileImageUri : ProfileImageState = ProfileImageState.Default,
    val name: String = "",
    val gender: Gender? = null,
    val birthDate: LocalDate? = null,
    val intimacy: Int = 1,
    val mbti: String = "",
    val personality: String = "",
    val firstMetDate: String = "",
    val firstMetPlace: String = ""
)
data class AddPersonAdditionalInfoState(
    val likes: List<String> = emptyList(),
    val likesDescription : String = "",
    val dislikes: List<String> = emptyList(),
    val dislikesDescription : String = "",
    val traits: List<String> = emptyList(),
    val traitsDescription: String = "",
    val lastContactDateText: String = "",
    val recentMetPlace: String = "",
    val memorableConversationTalk: String = "",
    val memoryImageUris: List<String> = emptyList()
)

data class AddPersonContractInfoState(
    val livingArea: String = "",
    val phoneNumber: String = "",
    val snsLink: String = "",
    val job: String = "",
    val memo: String = ""
)

data class AddPersonUiState(
    val coreInfo: AddPersonCoreInfoState = AddPersonCoreInfoState(),
    val additionalInfo: AddPersonAdditionalInfoState = AddPersonAdditionalInfoState(),
    val contactInfo: AddPersonContractInfoState = AddPersonContractInfoState()
){
    val canSubmit: Boolean
        get() = coreInfo.name.isNotBlank() &&
        coreInfo.gender != null &&
        coreInfo.birthDate != null &&
        coreInfo.mbti.isNotBlank() &&
        coreInfo.personality.isNotBlank() &&
        coreInfo.firstMetDate != null &&
        coreInfo.firstMetPlace.isNotBlank()
}

sealed interface AddPersonEvent {
    sealed interface CoreInfo : AddPersonEvent {
        data class ProfileImageChanged(val image: ProfileImageState) : CoreInfo
        data class BirthDateChanged(val value: LocalDate) : CoreInfo
    }
    data class NameChanged(val value: String) : AddPersonEvent
    data class GenderChanged(val value: Gender) : AddPersonEvent
    data class IntimacyChanged(val value: Int) : AddPersonEvent
    data class MbtiChanged(val value: String) : AddPersonEvent
    data class PersonalityChanged(val value: String) : AddPersonEvent
    data class FirstMetDateChanged(val value: String) : AddPersonEvent
    data class FirstMetPlaceChanged(val value: String) : AddPersonEvent

    sealed interface AdditionalInfo : AddPersonEvent {
        data class MemoryImagesAdded(val uris: List<String>) : AdditionalInfo
        data class MemoryImageRemoved(val uri: String) : AdditionalInfo
    }
    data object SaveClicked : AddPersonEvent

    /* data object BackClicked : AddPersonEvent
    data object ConfirmDiscardClicked : AddPersonEvent*/
}

sealed interface AddPersonEffect {
    data class ShowSnackbar(val message: String) : AddPersonEffect
    data object NavigateBack : AddPersonEffect
    data object LaunchProfileImagePicker : AddPersonEffect
    data object LaunchMemoryImagePicker : AddPersonEffect
}


