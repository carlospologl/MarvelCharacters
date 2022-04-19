package com.manimal.domain.response

sealed class UseCaseResult<out T : Any> {
    open class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    open class Failure : UseCaseResult<Nothing>()
}
