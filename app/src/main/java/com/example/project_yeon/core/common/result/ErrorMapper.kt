package com.example.project_yeon.core.common.result

import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.project_yeon.core.common.result.AppError
import java.io.IOException
import java.sql.SQLException

object ErrorMapper {

    fun map(throwable: Throwable): AppError {
        return when (throwable) {
            is IllegalArgumentException -> {
                AppError.Validation(message = throwable.message ?: "잘못된 입력입니다.")
            }

            is NoSuchElementException -> {
                AppError.NotFound(message = throwable.message ?: "대상을 찾을 수 없습니다.")
            }

            is SecurityException -> {
                AppError.Security(message = throwable.message ?: "보안 오류가 발생했습니다.")
            }

            is SQLException -> {
                AppError.Database(message = throwable.message ?: "데이터베이스 오류가 발생했습니다.")
            }

            is IOException -> {
                AppError.Unknown(message = throwable.message ?: "입출력 오류가 발생했습니다.")
            }

            else -> {
                AppError.Unknown(message = throwable.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        }
    }
}