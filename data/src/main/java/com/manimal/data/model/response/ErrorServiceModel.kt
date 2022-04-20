package com.manimal.data.model.response

import com.google.gson.annotations.SerializedName

data class ErrorServiceModel(
    @SerializedName("http_code") var httpCode: Int? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("message") var message: String? = null,
)
