package com.manimal.data.api

import com.manimal.data.model.response.CharactersListResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {

    @GET("v1/public/characters")
    suspend fun getCharactersList(
        @Query("ts") timeStamp: String,
        @Query("apikey") publicKey: String,
        @Query("hash") hash: String
    ): CharactersListResponseModel
}
