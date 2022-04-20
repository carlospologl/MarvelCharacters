package com.manimal.data.model.response

import com.google.gson.annotations.SerializedName

data class CharactersListDataModel(
    @SerializedName("offset") val offset : Int?,
    @SerializedName("limit") val limit : Int?,
    @SerializedName("total") val total : Int?,
    @SerializedName("count") val count : Int?,
    @SerializedName("results") val results : List<CharactersListResultsModel>?
)
