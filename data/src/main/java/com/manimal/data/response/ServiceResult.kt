package com.manimal.data.response

import com.manimal.data.model.response.ErrorServiceModel

sealed class ServiceResult<out T : Any> {
    open class Success<out T : Any>(val data: T) : ServiceResult<T>()
    open class BackendError(val error: ErrorServiceModel) : ServiceResult<Nothing>()
}

