package com.manimal.data.api

import com.manimal.data.model.response.CharactersListResponseModel
import retrofit2.http.GET

interface CharactersApi {

    @GET("/v1/public/characters")
    suspend fun getCharactersList(): CharactersListResponseModel
}
