package com.manimal.domain.model

data class ErrorModel(
    var httpCode: Int? = null,
    var code: String? = null,
    var message: String? = null,
)
