package com.example.project_yeon.domain.person.usecase

import com.example.project_yeon.feature.person.add.AddPersonUiState

data class ValidatePersonDraftResult(
    val isValid: Boolean,
    val message: String? = null
)

class ValidatePersonDraftUseCase {

    operator fun invoke(state: AddPersonUiState): ValidatePersonDraftResult {
        val core = state.coreInfo

        return when {
            core.name.isBlank() -> {
                ValidatePersonDraftResult(false, "이름을 입력해주세요.")
            }
            core.gender == null -> {
                ValidatePersonDraftResult(false, "성별을 선택해주세요.")
            }
            core.birthDate == null -> {
                ValidatePersonDraftResult(false, "생년월일을 입력해주세요.")
            }
            core.mbti.isBlank() -> {
                ValidatePersonDraftResult(false, "MBTI를 선택해주세요.")
            }
            core.personality.isBlank() -> {
                ValidatePersonDraftResult(false, "성격을 선택해주세요.")
            }
            core.firstMetDate.isBlank() -> {
                ValidatePersonDraftResult(false, "처음 만난 날을 입력해주세요.")
            }
            core.firstMetPlace.isBlank() -> {
                ValidatePersonDraftResult(false, "처음 만난 곳을 입력해주세요.")
            }
            else -> {
                ValidatePersonDraftResult(true)
            }
        }
    }
}