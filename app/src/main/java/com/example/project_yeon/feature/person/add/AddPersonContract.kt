package com.example.project_yeon.feature.person.add

import com.example.project_yeon.domain.person.model.PersonCreateRequest
import com.example.project_yeon.domain.person.model.PersonDraft
import com.example.project_yeon.domain.person.model.common.Gender
import com.example.project_yeon.feature.person.add.model.ProfileImageState
import java.time.LocalDate
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
){
    val isDirty: Boolean
        get() = name.isNotBlank() ||
                gender != null ||
                birthDate != null ||
                intimacy != 1 ||
                mbti.isNotBlank() ||
                personality.isNotBlank() ||
                firstMetDate.isNotBlank() ||
                firstMetPlace.isNotBlank() ||
                profileImageUri !is ProfileImageState.Default
}
data class AddPersonAdditionalInfoState(
    val personalityDescription: String = "",
    val likes: List<String> = emptyList(),
    val likesDescription : String = "",
    val dislikes: List<String> = emptyList(),
    val dislikesDescription : String = "",
    val traits: List<String> = emptyList(),
    val traitsDescription: String = "",
    val lastContactDateText: String = "",
    val recentMetPlace: String = "",
    val memorableConversationTalk: String = "",
    val memoryImageUris: List<String> = emptyList(),
    val job: String = "",
    val memo: String = ""
){
    val isDirty: Boolean
        get() = likes.isNotEmpty() ||
                likesDescription.isNotBlank() ||
                dislikes.isNotEmpty() ||
                dislikesDescription.isNotBlank() ||
                traits.isNotEmpty() ||
                traitsDescription.isNotBlank() ||
                lastContactDateText.isNotBlank() ||
                recentMetPlace.isNotBlank() ||
                memorableConversationTalk.isNotBlank() ||
                memoryImageUris.isNotEmpty() ||
                job.isNotBlank() ||
                memo.isNotBlank()
}

data class AddPersonContractInfoState(
    val livingArea: String = "",
    val phoneNumber: String = "",
    val snsLink: String = "",){
    val isDirty: Boolean
        get() = livingArea.isNotBlank() ||
                phoneNumber.isNotBlank() ||
                snsLink.isNotBlank()
}

data class AddPersonUiState(
    val coreInfo: AddPersonCoreInfoState = AddPersonCoreInfoState(),
    val additionalInfo: AddPersonAdditionalInfoState = AddPersonAdditionalInfoState(),
    val contactInfo: AddPersonContractInfoState = AddPersonContractInfoState(),
    val showDiscardDialog: Boolean = false,
){
    val canSubmit: Boolean
        get() = coreInfo.name.isNotBlank() &&
        coreInfo.gender != null &&
        coreInfo.birthDate != null &&
        coreInfo.mbti.isNotBlank() &&
        coreInfo.personality.isNotBlank() &&
        coreInfo.firstMetDate != null &&
        coreInfo.firstMetPlace.isNotBlank()

    val isDirty: Boolean
        get() = coreInfo.isDirty ||
                additionalInfo.isDirty ||
                contactInfo.isDirty

    fun toCreateRequest() : PersonCreateRequest {
        return PersonCreateRequest(
            name = coreInfo.name.trim(),
            gender = coreInfo.gender?.name.orEmpty(),
            birthDate = coreInfo.birthDate?.toString().orEmpty(),
            intimacy = coreInfo.intimacy,
            mbti = coreInfo.mbti,
            personality = coreInfo.personality,
            personalityDescription = additionalInfo.personalityDescription,
            firstMetDate = coreInfo.firstMetDate,
            firstMetPlace = coreInfo.firstMetPlace.trim(),

            likes = additionalInfo.likes,
            likesDescription = additionalInfo.likesDescription,
            dislikes = additionalInfo.dislikes,
            dislikesDescription = additionalInfo.dislikesDescription,
            traits = additionalInfo.traits,
            traitsDescription = additionalInfo.traitsDescription,

            lastContactDateText = additionalInfo.lastContactDateText,
            recentMetPlace = additionalInfo.recentMetPlace,
            memorableConversationTalk = additionalInfo.memorableConversationTalk,
            memoryImageUris = additionalInfo.memoryImageUris,

            livingArea = contactInfo.livingArea,
            phoneNumber = contactInfo.phoneNumber,
            snsLink = contactInfo.snsLink,
            job = additionalInfo.job,
            memo = additionalInfo.memo,

            profileImageUri = when (val image = coreInfo.profileImageUri) {
                is ProfileImageState.Custom -> image.uri
                ProfileImageState.Default -> null
            },
            createdAt = System.currentTimeMillis()
        )
    }
    fun toDraft(): PersonDraft {
        return PersonDraft(
            name = coreInfo.name.trim(),
            gender = coreInfo.gender,
            birthDate = coreInfo.birthDate,
            intimacy = coreInfo.intimacy,
            mbti = coreInfo.mbti,
            personality = coreInfo.personality,
            firstMetDate = coreInfo.firstMetDate.trim(),
            firstMetPlace = coreInfo.firstMetPlace.trim()
        )
    }


    fun AddPersonUiState.toDraft(): PersonDraft {
        return PersonDraft(
            name = coreInfo.name,
            gender = coreInfo.gender,
            birthDate = coreInfo.birthDate,
            intimacy = coreInfo.intimacy,
            mbti = coreInfo.mbti,
            personality = coreInfo.personality,
            firstMetDate = coreInfo.firstMetDate,
            firstMetPlace = coreInfo.firstMetPlace
        )
    }

}

sealed interface AddPersonEvent {
    sealed interface CoreInfo : AddPersonEvent {
        data class ProfileImageChanged(val image: ProfileImageState) : CoreInfo
        data class BirthDateChanged(val value: LocalDate) : CoreInfo
    }
    //CoreInfo Event
    data class NameChanged(val value: String) : AddPersonEvent
    data class GenderChanged(val value: Gender) : AddPersonEvent
    data class IntimacyChanged(val value: Int) : AddPersonEvent
    data class MbtiChanged(val value: String) : AddPersonEvent
    data class PersonalityChanged(val value: String) : AddPersonEvent
    data class FirstMetDateChanged(val value: String) : AddPersonEvent
    data class FirstMetPlaceChanged(val value: String) : AddPersonEvent


    //Addi~Contact Info Chagned

    data class LikesChanged(val value: List<String>) : AddPersonEvent
    data class LikesDescriptionChanged(val value: String) : AddPersonEvent
    data class DislikesChanged(val value: List<String>) : AddPersonEvent
    data class DislikesDescriptionChanged(val value: String) : AddPersonEvent
    data class TraitsChanged(val value: List<String>) : AddPersonEvent
    data class TraitsDescriptionChanged(val value: String) : AddPersonEvent
    data class PersonalityDescriptionChanged(val value: String) : AddPersonEvent
    data class LastContactDateChanged(val value: String) : AddPersonEvent
    data class LastRecentMetPlaceChanged(val value : String) : AddPersonEvent
    data class MemorableConversationTalkChanged(val value : String) : AddPersonEvent

    sealed interface AdditionalInfo : AddPersonEvent {
        data class MemoryImagesAdded(val uris: List<String>) : AdditionalInfo
        data class MemoryImageRemoved(val uri: String) : AdditionalInfo
    }
    data class LivingAreaChanged(val value: String) : AddPersonEvent
    data class PhoneNumberChanged(val value : String) : AddPersonEvent
    sealed interface ContactInfo : AddPersonEvent {
        data class SnsLinkChanged(val value: String) : ContactInfo
    }
    data class JobChanged(val value: String) : AddPersonEvent
    data class MemoChanged(val value: String) : AddPersonEvent

    data object SaveClicked : AddPersonEvent

    data object BackClicked : AddPersonEvent
    data object ConfirmDiscardClicked : AddPersonEvent
    data object DiscardCancel : AddPersonEvent
}

sealed interface AddPersonEffect {
    data class ShowSnackbar(val message: String) : AddPersonEffect
    data object NavigateBack : AddPersonEffect
    data object LaunchProfileImagePicker : AddPersonEffect
    data object LaunchMemoryImagePicker : AddPersonEffect
}


