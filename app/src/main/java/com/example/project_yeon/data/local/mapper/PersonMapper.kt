package com.example.project_yeon.data.local.mapper

import com.example.project_yeon.data.local.entity.PersonEntity
import com.example.project_yeon.domain.person.model.Person
import com.example.project_yeon.domain.person.model.PersonCreateRequest
import com.example.project_yeon.domain.person.model.PersonUpdateRequest

fun PersonEntity.toDomain(): Person {
    return Person(
        personId = personId,
        name = name,
        gender = gender,
        birthDate = birthDate,
        closeness = closeness,
        mbti = mbti,
        personality = personality,
        personalityDetail = personalityDetail,
        firstMetDay = firstMetDay,
        firstMetPlace = firstMetPlace,
        likes = likes,
        dislikes = dislikes,
        characteristics = characteristics,
        lastContactAt = lastContactAt,
        lastMetPlace = lastMetPlace,
        recentConversation = recentConversation,
        photos = photos,
        address = address,
        phone = phone,
        sns = sns,
        job = job,
        memo = memo,
        pinned = pinned,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun PersonCreateRequest.toEntity(
    createdAt: Long,
    updatedAt: Long
): PersonEntity {
    return PersonEntity(
        name = name,
        gender = gender,
        birthDate = birthDate.toString(),
        closeness = closeness,
        mbti = mbti,
        personality = personality,
        personalityDetail = personalityDetail,
        firstMetDay = firstMetDay,
        firstMetPlace = firstMetPlace,
        likes = likes,
        dislikes = dislikes,
        characteristics = characteristics,
        lastContactAt = lastContactAt,
        lastMetPlace = lastMetPlace,
        recentConversation = recentConversation,
        photos = photos,
        address = address,
        phone = phone,
        sns = sns,
        job = job,
        memo = memo,
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
        closeness = closeness,
        mbti = mbti,
        personality = personality,
        personalityDetail = personalityDetail,
        firstMetDay = firstMetDay,
        firstMetPlace = firstMetPlace,
        likes = likes,
        dislikes = dislikes,
        characteristics = characteristics,
        lastContactAt = lastContactAt,
        lastMetPlace = lastMetPlace,
        recentConversation = recentConversation,
        photos = photos,
        address = address,
        phone = phone,
        sns = sns,
        job = job,
        memo = memo,
        updatedAt = System.currentTimeMillis()
    )
}

