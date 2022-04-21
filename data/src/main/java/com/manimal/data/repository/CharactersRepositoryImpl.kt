package com.manimal.data.repository

import com.manimal.data.remote.CharactersRemoteDataSource
import com.manimal.domain.model.CharactersListData
import com.manimal.domain.repository.CharactersRepository
import com.manimal.domain.response.UseCaseResult

class CharactersRepositoryImpl(
    private val charactersRemoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {

    override suspend fun getCharactersList(timeStamp: String, publicKey: String, hash: String): UseCaseResult<CharactersListData> {
        return charactersRemoteDataSource.getCharactersList(timeStamp, publicKey, hash)
    }

    override suspend fun getCharacterDetail(
        characterId: Int,
        timeStamp: String,
        publicKey: String,
        hash: String
    ): UseCaseResult<CharactersListData> {
        return charactersRemoteDataSource.getCharacterDetail(characterId, timeStamp, publicKey, hash)
    }
}
