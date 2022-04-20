package com.manimal.data.model.response

import com.google.gson.annotations.SerializedName

data class CharactersListThumbnailModel(
    @SerializedName("path") val path : String?,
    @SerializedName("extension") val extension : String?
)
