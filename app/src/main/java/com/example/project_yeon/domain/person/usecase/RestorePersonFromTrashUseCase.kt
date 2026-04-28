package com.example.project_yeon.domain.person.usecase

import com.example.project_yeon.domain.person.repository.HiddenPersonRepository
import jakarta.inject.Inject

class RestorePersonFromTrashUseCase @Inject constructor(
    private val repository: HiddenPersonRepository
) {
    suspend operator fun invoke(personId: Long) {
        repository.restorePersonFromTrash(personId)
    }
}