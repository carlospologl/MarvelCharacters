package com.manimal.domain.usecase

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
            publicKey = publicKey,
            hash = md5(timeStamp + privateKey + publicKey)
        )
    }

    private fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    companion object {
        private const val publicKey = "871d315025aa0bf2b22f6ff52312bd39"
        private const val privateKey = "8b8dbd67d1f06ea68b668dd53b153d677a38a46b"
    }
}
