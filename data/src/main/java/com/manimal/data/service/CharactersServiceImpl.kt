package com.manimal.data.service

import com.manimal.data.api.CharactersApi
import com.manimal.data.api.NetworkModule
import com.manimal.data.model.response.CharactersListResponseModel
import com.manimal.data.response.ServiceResult
import java.lang.Exception

class CharactersServiceImpl : CharactersService {

    override suspend fun getCharactersList(): ServiceResult<CharactersListResponseModel> {
        val serviceApi = NetworkModule.getRetrofit().create(CharactersApi::class.java)
        return try {
            ServiceResult.Success(serviceApi.getCharactersList())
        } catch (e: Exception) {
            ServiceResult.BackendError()
        }
    }
}