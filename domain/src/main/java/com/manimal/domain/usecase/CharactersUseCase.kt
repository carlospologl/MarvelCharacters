package com.manimal.domain.usecase

import com.manimal.domain.BuildConfig
import com.manimal.domain.model.CharactersListData
import com.manimal.domain.repository.CharactersRepository
import com.manimal.domain.response.UseCaseResult
import com.manimal.domain.utils.SecurityUtils.md5
import java.util.Calendar

class CharactersUseCase(
    private val charactersRepository: CharactersRepository
) {
    suspend fun getCharactersList(): UseCaseResult<CharactersListData> {
        val timeStamp = Calendar.getInstance().timeInMillis.toString()

        return charactersRepository.getCharactersList(
            timeStamp = timeStamp,
            publicKey = BuildConfig.PUBLIC_KEY,
            hash = md5(timeStamp + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY)
        )
    }
    suspend fun getCharacterDetail(characterId: Int): UseCaseResult<CharactersListData> {
        val timeStamp = Calendar.getInstance().timeInMillis.toString()

        return charactersRepository.getCharacterDetail(
            characterId = characterId,
            timeStamp = timeStamp,
            publicKey = BuildConfig.PUBLIC_KEY,
            hash = md5(timeStamp + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY)
        )
    }
}
