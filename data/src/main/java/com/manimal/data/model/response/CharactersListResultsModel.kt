package com.manimal.data.model.response

import com.google.gson.annotations.SerializedName

data class CharactersListResultsModel(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("modified") val modified : String,
    @SerializedName("resourceURI") val resourceURI : String,
)
