package com.example.project_yeon.data.local.mapper

import com.example.project_yeon.data.local.entity.HiddenPersonEntity
import com.example.project_yeon.data.local.entity.PersonEntity
import com.example.project_yeon.domain.person.model.HiddenPerson
import com.example.project_yeon.feature.person.trash.model.TrashPersonUiModel
import com.google.gson.Gson


private val gson = Gson()

fun HiddenPersonEntity.toDomain(): HiddenPerson {
    return HiddenPerson(
        personId = personId,
        deletedAt = deletedAt,
        expiryAt = expiryAt,
        meta = meta // (나중에 파싱)
    )
}
fun HiddenPerson.toTrashPersonUiModel(): TrashPersonUiModel {
    val person = gson.fromJson(meta, PersonEntity::class.java)

    return TrashPersonUiModel(
        personId = personId,
        name = person.name,
        profileImageUri = person.profileImageUri,
        intimacy = person.intimacy,
        deletedAt = deletedAt,
        expiryAt = expiryAt
    )
}

fun PersonEntity.toHiddenEntity(
    deletedAt: Long,
    expiryAt: Long,
    meta: String
): HiddenPersonEntity {
    return HiddenPersonEntity(
        personId = personId,
        deletedAt = deletedAt,
        expiryAt = expiryAt,
        meta = meta
    )
}