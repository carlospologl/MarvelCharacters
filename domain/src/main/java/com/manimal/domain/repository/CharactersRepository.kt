package com.manimal.domain.repository

import com.manimal.domain.model.CharactersListData
import com.manimal.domain.response.UseCaseResult

interface CharactersRepository {

    suspend fun getCharactersLis() : UseCaseResult<CharactersListData>
}
