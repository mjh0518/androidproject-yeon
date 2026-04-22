package com.example.project_yeon.domain.person.usecase

import com.example.project_yeon.domain.person.repository.PersonRepository
import jakarta.inject.Inject

class UpdatePinnedStateUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    suspend operator fun invoke(
        personId: Long,
        pinned: Boolean,
        pinnedAt: Long?
    ) {
        repository.updatePinnedState(
            personId = personId,
            pinned = pinned,
            pinnedAt = pinnedAt
        )
    }
}