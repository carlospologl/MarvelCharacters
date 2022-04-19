package com.manimal.data.remote

import com.manimal.data.mapper.mapToCharactersListData
import com.manimal.data.model.response.CharactersListResponseModel
import com.manimal.data.response.ServiceResult
import com.manimal.data.service.CharactersService
import com.manimal.domain.model.CharactersListData
import com.manimal.domain.response.UseCaseResult

class CharactersRemoteDataSourceImpl(
    private val charactersService: CharactersService
) : CharactersRemoteDataSource {

    override suspend fun getCharactersList(): UseCaseResult<CharactersListData> {
        return when (val charactersResponse = charactersService.getCharactersList()) {
            is ServiceResult.Success<*> -> {
                UseCaseResult.Success(
                    (charactersResponse.data as CharactersListResponseModel).mapToCharactersListData()
                )
            }
            is ServiceResult.BackendError -> {
                UseCaseResult.Failure()
            }
        }
    }
}
