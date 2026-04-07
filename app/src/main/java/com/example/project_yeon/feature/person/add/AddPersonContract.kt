package com.example.project_yeon.feature.person.add

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
    val name: String = "",
    val gender: Gender? = Gender.MALE,
    val birthDate: String? = "",
    val intimacy: Int = 1,
    val mbti: String = "",
    val personality: String = "",
    val firstMetDate: String? = "",
    val firstMetPlace: String = ""
)
data class AddPersonUiState(
    val coreInfo: AddPersonCoreInfoState = AddPersonCoreInfoState(),
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
    data class NameChanged(val value: String) : AddPersonEvent
    data class GenderChanged(val value: Gender) : AddPersonEvent
    data class BirthDateChanged(val value: String) : AddPersonEvent
    data class IntimacyChanged(val value: Int) : AddPersonEvent
    data class MbtiChanged(val value: String) : AddPersonEvent
    data class PersonalityChanged(val value: String) : AddPersonEvent
    data class FirstMetDateChanged(val value: String) : AddPersonEvent
    data class FirstMetPlaceChanged(val value: String) : AddPersonEvent

    data object SaveClicked : AddPersonEvent

    /*sealed interface CoreInfo : AddPersonEvent {
        data class NameChanged(val value: String) : CoreInfo
        data class GenderChanged(val value: Int) : CoreInfo
        data class BirthDateChanged(val value: LocalDate) : CoreInfo
        data class IntimacyChanged(val value: Int) : CoreInfo
        data class MbtiChanged(val value: String) : CoreInfo
        data class PersonalityChanged(val value: String) : CoreInfo
        data class PersonalityDescriptionChanged(val value: String) : CoreInfo
        data class ProfileImageSelected(val uri: String?) : CoreInfo
    }

    sealed interface RelationInfo : AddPersonEvent {
        data class FirstMetDateChanged(val value: LocalDate) : RelationInfo
        data class FirstMetPlaceChanged(val value: String) : RelationInfo
        data class LikesChanged(val value: List<String>) : RelationInfo
        data class DislikesChanged(val value: List<String>) : RelationInfo
    }

    sealed interface AdditionalInfo : AddPersonEvent
    sealed interface ContactInfo : AddPersonEvent

    data object SaveClicked : AddPersonEvent
    data object BackClicked : AddPersonEvent
    data object ConfirmDiscardClicked : AddPersonEvent*/
}

sealed interface AddPersonEffect {
    data class ShowSnackbar(val message: String) : AddPersonEffect
    data object NavigateBack : AddPersonEffect
    data object LaunchProfileImagePicker : AddPersonEffect
    data object LaunchMemoryImagePicker : AddPersonEffect
}

