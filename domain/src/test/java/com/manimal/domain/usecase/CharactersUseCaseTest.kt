package com.manimal.domain.usecase

import com.manimal.domain.ConstantsTest.CHARACTER_ID
import com.manimal.domain.ConstantsTest.CODE
import com.manimal.domain.ConstantsTest.HTTP_CODE
import com.manimal.domain.ConstantsTest.MD5_RESULT
import com.manimal.domain.ConstantsTest.MESSAGE
import com.manimal.domain.ConstantsTest.PRIVATE_KEY
import com.manimal.domain.ConstantsTest.PUBLIC_KEY
import com.manimal.domain.ConstantsTest.TIME_STAMP
import com.manimal.domain.model.CharactersListData
import com.manimal.domain.model.ErrorModel
import com.manimal.domain.repository.CharactersRepository
import com.manimal.domain.response.UseCaseResult
import com.manimal.domain.utils.SecurityUtils
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CharactersUseCaseTest {
    private val charactersRepository = mock<CharactersRepository>()
    private val charactersUseCase: CharactersUseCase by lazy {
        CharactersUseCase(charactersRepository)
    }

    private val mockList = CharactersListData(10, 5, listOf())
    private val mockError = ErrorModel(HTTP_CODE, CODE, MESSAGE)

    @Test
    fun `validate success getCharactersList`() = runBlocking {
        val mockResult = UseCaseResult.Success(mockList)

        whenever(charactersRepository.getCharactersList(any(), any(), any()))
            .doReturn(mockResult)

        val repositoryResult = charactersUseCase.getCharactersList()
        verify(charactersRepository).getCharactersList(any(), any(), any())
        assert(repositoryResult == mockResult)
    }

    @Test
    fun `validate error getCharacterDetail`() = runBlocking {
        val mockError = UseCaseResult.Failure(mockError)

        whenever(charactersRepository.getCharacterDetail(any(), any(), any(), any()))
            .doReturn(mockError)

        val repositoryResult = charactersUseCase.getCharacterDetail(CHARACTER_ID)
        verify(charactersRepository).getCharacterDetail(any(), any(), any(), any())
        assert(repositoryResult == mockError)
        assert((repositoryResult as UseCaseResult.Failure).errorModel.httpCode == HTTP_CODE)
    }

    @Test
    fun `validate md5 method`() {
        assert(SecurityUtils.md5(TIME_STAMP + PRIVATE_KEY + PUBLIC_KEY) == MD5_RESULT)
    }
}
