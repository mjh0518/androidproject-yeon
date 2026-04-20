package com.example.project_yeon.domain.person.usecase

import com.example.project_yeon.core.common.result.ResultWrapper
import com.example.project_yeon.domain.person.model.PersonUpdateRequest
import com.example.project_yeon.domain.person.repository.PersonRepository
import jakarta.inject.Inject

class ModifyPersonUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend operator fun invoke(request: PersonUpdateRequest): ResultWrapper<Unit> {
        return personRepository.updatePerson(request)
    }
}