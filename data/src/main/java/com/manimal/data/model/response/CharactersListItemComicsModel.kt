package com.manimal.data.model.response

import com.google.gson.annotations.SerializedName

data class CharactersListItemComicsModel(
    @SerializedName("resourceURI") val resourceURI : String?,
    @SerializedName("name") val name : String?
)
