package com.example.project_yeon.domain.person.usecase

import com.example.project_yeon.core.common.result.ResultWrapper
import com.example.project_yeon.domain.person.model.PersonCreateRequest
import com.example.project_yeon.domain.person.repository.PersonRepository

class CreatePersonUseCase(
    private val personRepository: PersonRepository
) {
    suspend operator fun invoke(request: PersonCreateRequest): ResultWrapper<Long> {
        return personRepository.addPerson(request)
    }
}