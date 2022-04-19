package com.manimal.data.repository

import com.manimal.data.remote.CharactersRemoteDataSource
import com.manimal.domain.model.CharactersListData
import com.manimal.domain.repository.CharactersRepository
import com.manimal.domain.response.UseCaseResult

class CharactersRepositoryImpl(
    private val charactersRemoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {

    override suspend fun getCharactersLis(): UseCaseResult<CharactersListData> {
        return charactersRemoteDataSource.getCharactersList()
    }
}
