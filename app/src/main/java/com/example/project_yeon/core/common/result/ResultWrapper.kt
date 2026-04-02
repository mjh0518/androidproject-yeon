package com.example.project_yeon.core.common.result

import com.example.yeon.core.common.result.AppError

sealed interface ResultWrapper<out T> {

    data class Success<T>(
        val data: T
    ) : ResultWrapper<T>

    data class Error(
        val error: AppError
    ) : ResultWrapper<Nothing>
}