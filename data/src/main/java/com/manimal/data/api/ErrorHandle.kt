package com.manimal.data.api

import com.google.gson.Gson
import com.manimal.data.model.response.ErrorServiceModel
import retrofit2.HttpException
import java.net.UnknownHostException

object ErrorHandle {
    private const val UNKNOWN_HOST = "Offline"

    fun getApiError(e: Exception): ErrorServiceModel {
        return when (e) {
            is HttpException -> manageHttpException(e)
            is UnknownHostException -> ErrorServiceModel(httpCode = 0, code = UNKNOWN_HOST, message = e.message)
            else -> ErrorServiceModel(httpCode = 0, code = e.cause.toString(), message = e.message)
        }
    }

    private fun manageHttpException(exception: HttpException): ErrorServiceModel {
        var jsonString = ""
        exception.response()?.let {
            jsonString = it.errorBody()?.string() ?: ""
        }
        val error: ErrorServiceModel = try {
            val errorModel = Gson().fromJson(jsonString, ErrorServiceModel::class.java)
            if (errorModel.code.isNullOrBlank()) errorModel.code = jsonString
            if (errorModel.message.isNullOrBlank()) errorModel.message = jsonString
            errorModel
        } catch (e: Exception) {
            ErrorServiceModel(code = jsonString, message = jsonString)
        }
        error.httpCode = exception.code()

        return error
    }
}