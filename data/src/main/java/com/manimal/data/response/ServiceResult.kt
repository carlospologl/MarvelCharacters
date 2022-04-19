package com.manimal.data.response

sealed class ServiceResult<out T : Any> {
    open class Success<out T : Any>(val data: T) : ServiceResult<T>()
    open class BackendError : ServiceResult<Nothing>()
}

