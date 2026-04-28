package com.example.project_yeon.domain.person.usecase

import com.example.project_yeon.domain.person.repository.HiddenPersonRepository
import jakarta.inject.Inject

class GetHiddenPersonsUseCase @Inject constructor(
    private val repository: HiddenPersonRepository
) {
    operator fun invoke() = repository.getHiddenPersons()
}