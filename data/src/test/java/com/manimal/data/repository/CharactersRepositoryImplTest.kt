package com.manimal.data.repository

import com.manimal.data.remote.CharactersRemoteDataSource
import com.manimal.domain.model.CharactersListData
import com.manimal.domain.repository.CharactersRepository
import com.manimal.domain.response.UseCaseResult
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CharactersRepositoryImplTest {
    private val dataSource = mock<CharactersRemoteDataSource>()
    private val charactersRepository: CharactersRepository by lazy {
        CharactersRepositoryImpl(dataSource)
    }

    private val mockList = CharactersListData(10, 5, listOf())

    @Test
    fun `validate success getCharactersList`() = runBlocking {
        val timeStamp = ""
        val publicKey = ""
        val hash = ""

        val mockResult = UseCaseResult.Success(mockList)

        whenever(dataSource.getCharactersList(timeStamp, publicKey, hash))
            .doReturn(mockResult)

        val repositoryResult = charactersRepository.getCharactersList(timeStamp, publicKey, hash)
        verify(dataSource).getCharactersList(timeStamp, publicKey, hash)
        assert(repositoryResult == mockResult)
    }
}
