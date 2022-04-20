package com.manimal.data.service

import com.manimal.data.model.response.CharactersListResponseModel
import com.manimal.data.response.ServiceResult

interface CharactersService {

    suspend fun getCharactersList(timeStamp: String, publicKey: String, hash: String): ServiceResult<CharactersListResponseModel>
    suspend fun getCharacterDetail(characterId: Int, timeStamp: String, publicKey: String, hash: String): ServiceResult<CharactersListResponseModel>
}