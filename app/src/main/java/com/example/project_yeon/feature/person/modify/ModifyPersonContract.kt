package com.example.project_yeon.feature.person.modify
import com.example.project_yeon.domain.person.model.PersonDraft
import com.example.project_yeon.domain.person.model.PersonUpdateRequest
import com.example.project_yeon.domain.person.model.common.Gender
import com.example.project_yeon.feature.person.add.model.ProfileImageState
import java.time.LocalDate
data class ModifyPersonCoreInfoState(
    val profileImageUri: ProfileImageState = ProfileImageState.Default,
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
data class ModifyPersonAdditionalInfoState(
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

data class ModifyPersonContractInfoState(
    val livingArea: String = "",
    val phoneNumber: String = "",
    val snsLink: String = "",){
    val isDirty: Boolean
        get() = livingArea.isNotBlank() ||
                phoneNumber.isNotBlank() ||
                snsLink.isNotBlank()
}

data class ModifyPersonUiState(
    val personId : Long? = null,
    val originalCoreInfo: ModifyPersonCoreInfoState? = null,
    val originalAdditionalInfo: ModifyPersonAdditionalInfoState? = null,
    val originalContactInfo: ModifyPersonContractInfoState? = null,

    val coreInfo: ModifyPersonCoreInfoState = ModifyPersonCoreInfoState(),
    val additionalInfo: ModifyPersonAdditionalInfoState = ModifyPersonAdditionalInfoState(),
    val contactInfo: ModifyPersonContractInfoState = ModifyPersonContractInfoState(),

    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val isSaving: Boolean = false,
    val showDiscardDialog: Boolean = false,
) {
    val isDirty: Boolean
        get() = personId != null &&
                originalCoreInfo != null &&
                originalAdditionalInfo != null &&
                originalContactInfo != null &&
                (
                        coreInfo != originalCoreInfo ||
                                additionalInfo != originalAdditionalInfo ||
                                contactInfo != originalContactInfo
                        )

    val isValid: Boolean
        get() = coreInfo.name.isNotBlank() &&
                coreInfo.gender != null &&
                coreInfo.birthDate != null &&
                coreInfo.mbti.isNotBlank() &&
                coreInfo.personality.isNotBlank() &&
                coreInfo.firstMetDate.isNotBlank() &&
                coreInfo.firstMetPlace.isNotBlank()

    val canSubmit: Boolean
        get() = isValid && isDirty && !isLoading && !isSaving

    fun toUpdateRequest(): PersonUpdateRequest {
        return PersonUpdateRequest(
            personId = personId ?: error("personId is required"),
            name = coreInfo.name.trim(),
            gender = coreInfo.gender?.name.orEmpty(),
            birthDate = coreInfo.birthDate?.toString().orEmpty(),
            intimacy = coreInfo.intimacy,
            mbti = coreInfo.mbti,
            personality = coreInfo.personality,
            personalityDescription = additionalInfo.personalityDescription,
            firstMetDate = coreInfo.firstMetDate.trim(),
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
            pinned = false
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

}


sealed interface ModifyPersonEvent {
    sealed interface CoreInfo : ModifyPersonEvent {
        data class ProfileImageChanged(val image: ProfileImageState) : CoreInfo
        data class BirthDateChanged(val value: LocalDate) : CoreInfo
    }
    //CoreInfo Event
    data class NameChanged(val value: String) : ModifyPersonEvent
    data class GenderChanged(val value: Gender) : ModifyPersonEvent
    data class IntimacyChanged(val value: Int) : ModifyPersonEvent
    data class MbtiChanged(val value: String) : ModifyPersonEvent
    data class PersonalityChanged(val value: String) : ModifyPersonEvent
    data class FirstMetDateChanged(val value: String) : ModifyPersonEvent
    data class FirstMetPlaceChanged(val value: String) : ModifyPersonEvent


    //Addi~Contact Info Chagned

    data class LikesChanged(val value: List<String>) : ModifyPersonEvent
    data class LikesDescriptionChanged(val value: String) : ModifyPersonEvent
    data class DislikesChanged(val value: List<String>) : ModifyPersonEvent
    data class DislikesDescriptionChanged(val value: String) : ModifyPersonEvent
    data class TraitsChanged(val value: List<String>) : ModifyPersonEvent
    data class TraitsDescriptionChanged(val value: String) : ModifyPersonEvent
    data class PersonalityDescriptionChanged(val value: String) : ModifyPersonEvent
    data class LastContactDateChanged(val value: String) : ModifyPersonEvent
    data class LastRecentMetPlaceChanged(val value : String) : ModifyPersonEvent
    data class MemorableConversationTalkChanged(val value : String) : ModifyPersonEvent

    sealed interface AdditionalInfo : ModifyPersonEvent {
        data class MemoryImagesAdded(val uris: List<String>) : AdditionalInfo
        data class MemoryImageRemoved(val uri: String) : AdditionalInfo
    }
    data class LivingAreaChanged(val value: String) : ModifyPersonEvent
    data class PhoneNumberChanged(val value : String) : ModifyPersonEvent
    sealed interface ContactInfo : ModifyPersonEvent {
        data class SnsLinkChanged(val value: String) : ContactInfo
    }
    data class JobChanged(val value: String) : ModifyPersonEvent
    data class MemoChanged(val value: String) : ModifyPersonEvent

    data object SaveClicked : ModifyPersonEvent

    data object BackClicked : ModifyPersonEvent
    data object ConfirmDiscardClicked : ModifyPersonEvent
    data object DiscardCancel : ModifyPersonEvent
}

sealed interface ModifyPersonEffect {
    data class ShowSnackbar(val message: String) : ModifyPersonEffect
    data object NavigateBack : ModifyPersonEffect
    data object LaunchProfileImagePicker : ModifyPersonEffect
    data object LaunchMemoryImagePicker : ModifyPersonEffect
}

