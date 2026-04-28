package com.example.project_yeon.domain.person.usecase

import com.example.project_yeon.domain.person.repository.PersonRepository
import jakarta.inject.Inject

class MoveToTrashUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    suspend operator fun invoke(personIds: List<Long>) {
        repository.moveToTrash(personIds)
    }
}