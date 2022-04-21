package com.manimal.data.api

import com.manimal.data.model.response.CharactersListResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    @GET("v1/public/characters")
    suspend fun getCharactersList(
        @Query("ts") timeStamp: String,
        @Query("apikey") publicKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: String? = "50"
    ): CharactersListResponseModel

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: Int,
        @Query("ts") timeStamp: String,
        @Query("apikey") publicKey: String,
        @Query("hash") hash: String
    ): CharactersListResponseModel
}
