package com.manimal.data.service

import com.manimal.data.model.response.CharactersListResponseModel
import com.manimal.data.response.ServiceResult

interface CharactersService {

    suspend fun getCharactersList(): ServiceResult<CharactersListResponseModel>
}