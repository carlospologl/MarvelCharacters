package com.manimal.data.model.response

import com.google.gson.annotations.SerializedName

data class CharactersListComicsModel(
    @SerializedName("available") val available : Int?,
    @SerializedName("collectionURI") val collectionURI : String?,
    @SerializedName("items") val items : List<CharactersListItemComicsModel>?
)
