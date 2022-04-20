package com.manimal.domain.repository

import com.manimal.domain.model.CharactersListData
import com.manimal.domain.response.UseCaseResult

interface CharactersRepository {

    suspend fun getCharactersLis(timeStamp: String, publicKey: String, hash: String) : UseCaseResult<CharactersListData>
    suspend fun getCharacterDetail(characterId: Int, timeStamp: String, publicKey: String, hash: String) : UseCaseResult<CharactersListData>
}
