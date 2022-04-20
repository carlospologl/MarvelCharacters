package com.manimal.data.service

import com.manimal.data.api.CharactersApi
import com.manimal.data.api.ErrorHandle.getApiError
import com.manimal.data.api.NetworkModule
import com.manimal.data.model.response.CharactersListResponseModel
import com.manimal.data.response.ServiceResult
import java.lang.Exception

class CharactersServiceImpl : CharactersService {

    override suspend fun getCharactersList(timeStamp: String, publicKey: String, hash: String): ServiceResult<CharactersListResponseModel> {
        val serviceApi = NetworkModule.getRetrofit().create(CharactersApi::class.java)
        return try {
            ServiceResult.Success(serviceApi.getCharactersList(timeStamp, publicKey, hash))
        } catch (e: Exception) {
            ServiceResult.BackendError(getApiError(e))
        }
    }

    override suspend fun getCharacterDetail(
        characterId: Int,
        timeStamp: String,
        publicKey: String,
        hash: String
    ): ServiceResult<CharactersListResponseModel> {
        val serviceApi = NetworkModule.getRetrofit().create(CharactersApi::class.java)
        return try {
            ServiceResult.Success(serviceApi.getCharacterDetail(characterId, timeStamp, publicKey, hash))
        } catch (e: Exception) {
            ServiceResult.BackendError(getApiError(e))
        }
    }
}