package com.example.project_yeon.core.common.result

suspend inline fun <T> safeCall(
    crossinline action: suspend () -> T
): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(action())
    } catch (throwable: Throwable) {
        ResultWrapper.Error(ErrorMapper.map(throwable))
    }
}