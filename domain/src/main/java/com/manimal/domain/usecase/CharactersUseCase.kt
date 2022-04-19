package com.manimal.domain.usecase

import com.manimal.domain.repository.CharactersRepository

class CharactersUseCase(
    private val charactersRepository: CharactersRepository
) {
    suspend fun getCharactersList() =
        charactersRepository.getCharactersLis()
}
