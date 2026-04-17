package com.example.project_yeon.domain.person.usecase

import com.example.project_yeon.core.common.result.ResultWrapper
import com.example.project_yeon.domain.person.model.Person
import com.example.project_yeon.domain.person.repository.PersonRepository
import jakarta.inject.Inject

class GetPersonDetailUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend operator fun invoke(personId: Long): Person {
        return personRepository.getPerson(personId)
    }
}