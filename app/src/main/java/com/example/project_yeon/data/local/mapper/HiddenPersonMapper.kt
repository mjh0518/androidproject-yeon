package com.example.project_yeon.data.local.mapper

import com.example.project_yeon.data.local.entity.HiddenPersonEntity
import com.example.project_yeon.data.local.entity.PersonEntity
import com.example.project_yeon.domain.person.model.HiddenPerson

fun HiddenPersonEntity.toDomain(): HiddenPerson {
    return HiddenPerson(
        personId = personId,
        deletedAt = deletedAt,
        expiryAt = expiryAt,
        meta = meta // (나중에 파싱)
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