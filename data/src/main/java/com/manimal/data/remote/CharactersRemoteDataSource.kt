package com.manimal.data.remote

import com.manimal.domain.model.CharactersListData
import com.manimal.domain.response.UseCaseResult

interface CharactersRemoteDataSource {

    suspend fun getCharactersList(): UseCaseResult<CharactersListData>
}
