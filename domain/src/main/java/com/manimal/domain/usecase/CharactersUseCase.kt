package com.manimal.domain.usecase

import com.manimal.domain.BuildConfig
import com.manimal.domain.model.CharactersListData
import com.manimal.domain.repository.CharactersRepository
import com.manimal.domain.response.UseCaseResult
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Calendar

class CharactersUseCase(
    private val charactersRepository: CharactersRepository
) {
    suspend fun getCharactersList(): UseCaseResult<CharactersListData> {
        val timeStamp = Calendar.getInstance().timeInMillis.toString()

        return charactersRepository.getCharactersLis(
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

    private fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}
