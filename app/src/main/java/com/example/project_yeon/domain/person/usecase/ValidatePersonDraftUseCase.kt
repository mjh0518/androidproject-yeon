package com.example.project_yeon.domain.person.usecase

import com.example.project_yeon.domain.person.model.PersonDraft
import jakarta.inject.Inject

data class ValidatePersonDraftResult(
    val isValid: Boolean,
    val message: String? = null
)

class ValidatePersonDraftUseCase @Inject constructor() {
    operator fun invoke(draft: PersonDraft): ValidatePersonDraftResult {
        if (draft.name.isBlank()) {
            return ValidatePersonDraftResult(false, "이름을 입력해주세요.")
        }
        if (draft.gender == null) {
            return ValidatePersonDraftResult(false, "성별을 선택해주세요.")
        }
        if (draft.birthDate == null) {
            return ValidatePersonDraftResult(false, "생년월일을 입력해주세요.")
        }
        if (draft.mbti.isBlank()) {
            return ValidatePersonDraftResult(false, "MBTI를 선택해주세요.")
        }
        if (draft.personality.isBlank()) {
            return ValidatePersonDraftResult(false, "성격을 선택해주세요.")
        }
        if (draft.firstMetDate.isBlank()) {
            return ValidatePersonDraftResult(false, "처음 만난 날짜를 입력해주세요.")
        }
        if (draft.firstMetPlace.isBlank()) {
            return ValidatePersonDraftResult(false, "처음 만난 곳을 입력해주세요.")
        }

        return ValidatePersonDraftResult(true, null)
    }
}