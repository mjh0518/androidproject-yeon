package com.example.project_yeon.core.common.result

sealed interface AppError {

    data class Validation(
        val field: String? = null,
        val message: String
    ) : AppError

    data class Database(
        val message: String = "데이터 처리 중 오류가 발생했습니다."
    ) : AppError

    data class Security(
        val message: String = "보안 처리 중 오류가 발생했습니다."
    ) : AppError

    data class NotFound(
        val message: String = "데이터를 찾을 수 없습니다."
    ) : AppError

    data class Unknown(
        val message: String = "알 수 없는 오류가 발생했습니다."
    ) : AppError
}