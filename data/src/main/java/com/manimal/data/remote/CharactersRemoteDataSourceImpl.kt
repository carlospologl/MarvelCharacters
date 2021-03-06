package com.manimal.data.remote

import com.manimal.data.mapper.mapToCharactersListData
import com.manimal.data.model.response.CharactersListResponseModel
import com.manimal.data.response.ServiceResult
import com.manimal.data.service.CharactersService
import com.manimal.domain.model.CharactersListData
import com.manimal.domain.model.ErrorModel
import com.manimal.domain.response.UseCaseResult

class CharactersRemoteDataSourceImpl(
    private val charactersService: CharactersService
) : CharactersRemoteDataSource {

    override suspend fun getCharactersList(timeStamp: String, publicKey: String, hash: String): UseCaseResult<CharactersListData> {
        return when (val charactersResponse = charactersService.getCharactersList(timeStamp, publicKey, hash)) {
            is ServiceResult.Success<*> -> {
                UseCaseResult.Success(
                    (charactersResponse.data as CharactersListResponseModel).mapToCharactersListData()
                )
            }
            is ServiceResult.BackendError -> {
                UseCaseResult.Failure(
                    ErrorModel(
                        httpCode = charactersResponse.error.httpCode,
                        code = charactersResponse.error.code,
                        message = charactersResponse.error.message
                    )
                )
            }
        }
    }

    override suspend fun getCharacterDetail(
        characterId: Int,
        timeStamp: String,
        publicKey: String,
        hash: String
    ): UseCaseResult<CharactersListData> {
        return when (val charactersResponse = charactersService.getCharacterDetail(characterId, timeStamp, publicKey, hash)) {
            is ServiceResult.Success<*> -> {
                UseCaseResult.Success(
                    (charactersResponse.data as CharactersListResponseModel).mapToCharactersListData()
                )
            }
            is ServiceResult.BackendError -> {
                UseCaseResult.Failure(
                    ErrorModel(
                        httpCode = charactersResponse.error.httpCode,
                        code = charactersResponse.error.code,
                        message = charactersResponse.error.message
                    )
                )
            }
        }
    }
}
