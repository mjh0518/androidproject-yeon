package com.example.project_yeon.core.common.result
sealed interface ResultWrapper<out T> {

    data class Success<T>(
        val data: T
    ) : ResultWrapper<T>

    data class Error(
        val error: AppError
    ) : ResultWrapper<Nothing>
}