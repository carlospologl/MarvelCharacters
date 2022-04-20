package com.manimal.domain.response

import com.manimal.domain.model.ErrorModel

sealed class UseCaseResult<out T : Any> {
    open class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    open class Failure(val errorModel: ErrorModel) : UseCaseResult<Nothing>()
}
