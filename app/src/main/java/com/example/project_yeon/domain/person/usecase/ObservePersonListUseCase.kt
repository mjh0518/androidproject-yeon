package com.example.project_yeon.domain.person.usecase

import com.example.project_yeon.domain.person.model.PersonListItem
import com.example.project_yeon.domain.person.repository.PersonRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObservePersonListUseCase@Inject constructor(
    private val repository: PersonRepository
) {
    operator fun invoke(): Flow<List<PersonListItem>> {
        return repository.getPersons().map { persons ->
            persons.map { person ->
                PersonListItem(
                    personId = person.personId,
                    name = person.name,
                    profileImageUri = person.profileImageUri,
                    intimacy = person.intimacy,
                    isPinned = person.pinned,
                    birthDateText = person.birthDate,
                    mbtiText = person.mbti,
                    genderText = person.gender,
                    personalityText = person.personality,
                    jobText = person.job,
                    recentMeetPlaceText = person.recentMetPlace,
                    lastContactDateText = person.lastContactDateText,
                    createdAt = person.createdAt,
                )
            }
        }
    }
}