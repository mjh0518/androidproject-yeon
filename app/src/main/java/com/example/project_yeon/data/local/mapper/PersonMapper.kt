package com.example.project_yeon.data.local.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.project_yeon.data.local.entity.PersonEntity
import com.example.project_yeon.domain.person.model.Person
import com.example.project_yeon.domain.person.model.PersonCreateRequest
import com.example.project_yeon.domain.person.model.PersonUpdateRequest
import com.example.project_yeon.feature.person.add.model.ProfileImageState
import com.example.project_yeon.feature.person.detail.DetailPersonUiModel
import com.example.project_yeon.domain.person.model.common.Gender
import com.example.project_yeon.feature.person.modify.ModifyPersonAdditionalInfoState
import com.example.project_yeon.feature.person.modify.ModifyPersonContractInfoState
import com.example.project_yeon.feature.person.modify.ModifyPersonCoreInfoState
import org.json.JSONArray
import java.time.LocalDate

private fun List<String>.toJsonString(): String? {
    return if (isEmpty()) null else JSONArray(this).toString()
}

private fun String?.toStringList(): List<String> {
    if (this.isNullOrBlank()) return emptyList()

    return try {
        val jsonArray = JSONArray(this)
        List(jsonArray.length()) { index ->
            jsonArray.getString(index)
        }
    } catch (e: Exception) {
        emptyList()
    }
}

fun PersonEntity.toDomain(): Person {
    return Person(
        personId = personId,
        name = name,
        gender = gender,
        birthDate = birthDate,
        intimacy = intimacy,
        mbti = mbti,
        personality = personality,
        personalityDescription = personalityDescription,
        firstMetDate = firstMetDate,
        firstMetPlace = firstMetPlace,

        likes = likes.toStringList(),
        likesDescription = likesDescription ?: "",
        dislikes = dislikes.toStringList(),
        dislikesDescription = dislikesDescription ?: "",
        traits = traits.toStringList(),
        traitsDescription = traitsDescription ?: "",

        lastContactDateText = lastContactDateText ?: "",
        recentMetPlace = recentMetPlace ?: "",
        memorableConversationTalk = memorableConversationTalk ?: "",
        memoryImageUris = memoryImageUris.toStringList(),

        livingArea = livingArea ?: "",
        phoneNumber = phoneNumber ?: "",
        snsLink = snsLink ?: "",
        job = job ?: "",
        memo = memo ?: "",

        profileImageUri = profileImageUri,

        pinned = pinned,
        pinnedAt = pinnedAt,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}

fun PersonCreateRequest.toEntity(
    createdAt: Long,
    updatedAt: Long
): PersonEntity {
    return PersonEntity(
        name = name,
        gender = gender,
        birthDate = birthDate,
        intimacy = intimacy,
        mbti = mbti,
        personality = personality,
        personalityDescription = personalityDescription.ifBlank { null },
        firstMetDate = firstMetDate,
        firstMetPlace = firstMetPlace,

        likes = likes.toJsonString(),
        likesDescription = likesDescription.ifBlank { null },
        dislikes = dislikes.toJsonString(),
        dislikesDescription = dislikesDescription.ifBlank { null },
        traits = traits.toJsonString(),
        traitsDescription = traitsDescription.ifBlank { null },

        lastContactDateText = lastContactDateText.ifBlank { null },
        recentMetPlace = recentMetPlace.ifBlank { null },
        memorableConversationTalk = memorableConversationTalk.ifBlank { null },
        memoryImageUris = memoryImageUris.toJsonString(),

        livingArea = livingArea.ifBlank { null },
        phoneNumber = phoneNumber.ifBlank { null },
        snsLink = snsLink.ifBlank { null },
        job = job.ifBlank { null },
        memo = memo.ifBlank { null },

        profileImageUri = profileImageUri,

        pinned = false,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun PersonUpdateRequest.toEntity(old: PersonEntity): PersonEntity {
    return old.copy(
        name = name,
        gender = gender,
        birthDate = birthDate,
        intimacy = intimacy,
        mbti = mbti,
        personality = personality,
        personalityDescription = personalityDescription.ifBlank { null },
        firstMetDate = firstMetDate,
        firstMetPlace = firstMetPlace,

        likes = likes.toJsonString(),
        likesDescription = likesDescription.ifBlank { null },
        dislikes = dislikes.toJsonString(),
        dislikesDescription = dislikesDescription.ifBlank { null },
        traits = traits.toJsonString(),
        traitsDescription = traitsDescription.ifBlank { null },

        lastContactDateText = lastContactDateText.ifBlank { null },
        recentMetPlace = recentMetPlace.ifBlank { null },
        memorableConversationTalk = memorableConversationTalk.ifBlank { null },
        memoryImageUris = memoryImageUris.toJsonString(),

        livingArea = livingArea.ifBlank { null },
        phoneNumber = phoneNumber.ifBlank { null },
        snsLink = snsLink.ifBlank { null },
        job = job.ifBlank { null },
        memo = memo.ifBlank { null },

        profileImageUri = profileImageUri,
        pinned = pinned,

        updatedAt = System.currentTimeMillis()
    )
}

fun Person.toDetailPersonUiModel(): DetailPersonUiModel {
    return DetailPersonUiModel(
        id = personId,
        profileImageUri = profileImageUri,
        name = name,
        intimacy = intimacy,
        gender = gender,
        birthDate = birthDate,
        mbti = mbti,
        personality = personality,
        personalityDescription = personalityDescription,
        job = job,
        firstMetDate = firstMetDate,
        firstMetPlace = firstMetPlace,
        likes = likes,
        dislikes = dislikes,
        traits = traits,
        likesDescription = likesDescription,
        dislikesDescription = dislikesDescription,
        traitsDescription = traitsDescription,
        lastContactDate = lastContactDateText,
        recentMetPlace = recentMetPlace,
        recentTalk = memorableConversationTalk,
        memoryImageUris = memoryImageUris,
        phone = phoneNumber,
        address = livingArea,
        sns = snsLink,
        memo = memo,
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun Person.toModifyCoreInfoState(): ModifyPersonCoreInfoState {
    return ModifyPersonCoreInfoState(
        profileImageUri = profileImageUri
            ?.let { ProfileImageState.Custom(it) }
            ?: ProfileImageState.Default,
        name = name,
        gender = gender.toModifyGender(),
        birthDate = birthDate.toLocalDateOrNull(),
        intimacy = intimacy,
        mbti = mbti,
        personality = personality,
        firstMetDate = firstMetDate,
        firstMetPlace = firstMetPlace
    )
}

fun Person.toModifyAdditionalInfoState(): ModifyPersonAdditionalInfoState {
    return ModifyPersonAdditionalInfoState(
        personalityDescription = personalityDescription ?: "",
        likes = likes,
        likesDescription = likesDescription ?: "",
        dislikes = dislikes,
        dislikesDescription = dislikesDescription ?: "",
        traits = traits,
        traitsDescription = traitsDescription ?: "",
        lastContactDateText = lastContactDateText ?: "",
        recentMetPlace = recentMetPlace ?: "",
        memorableConversationTalk = memorableConversationTalk?: "",
        memoryImageUris = memoryImageUris,
        job = job ?: "",
        memo = memo?: ""
    )
}

fun Person.toModifyContactInfoState(): ModifyPersonContractInfoState {
    return ModifyPersonContractInfoState(
        livingArea = livingArea?: "",
        phoneNumber = phoneNumber?: "",
        snsLink = snsLink?: ""
    )
}

private fun String.toModifyGender(): Gender? {
    return when (this) {
        "MALE" -> Gender.MALE
        "FEMALE" -> Gender.FEMALE
        else -> null
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun String?.toLocalDateOrNull(): LocalDate? {
    if (this.isNullOrBlank()) return null
    return runCatching { LocalDate.parse(this) }.getOrNull()
}