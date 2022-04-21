package com.manimal.marvelcharacters.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.manimal.domain.model.CharactersListData
import com.manimal.domain.response.UseCaseResult
import com.manimal.domain.usecase.CharactersUseCase
import com.manimal.marvelcharacters.features.base.BaseStatus
import com.manimal.marvelcharacters.features.characters.CharactersViewModel
import com.manimal.marvelcharacters.features.characters.list.CharactersListDataContainer
import com.manimal.marvelcharacters.testObserver
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val charactersUseCase = mock<CharactersUseCase>()
    private val charactersViewModel = CharactersViewModel(charactersUseCase)

    private val mockCharactersListData = CharactersListData(0, 0, listOf())
    private val mockResponse = CharactersListDataContainer(BaseStatus.SUCCESS, mockCharactersListData)

    @Test
    fun `validate success getCharactersList`() {
        val charactersList = mock<LiveData<CharactersListDataContainer>>()

        val testObserver = charactersViewModel.charactersListLiveData.testObserver() {
            runBlocking {
                whenever(charactersList.value).thenReturn(mockResponse)
                whenever(charactersUseCase.getCharactersList()).doReturn(UseCaseResult.Success(mockCharactersListData))

                charactersViewModel.getCharactersList()

                verify(charactersUseCase).getCharactersList()
            }

        }
        assert(charactersList.value?.data == charactersViewModel.charactersListLiveData.value?.data)
        assert(testObserver.observedValues[1].status == BaseStatus.SUCCESS)
    }
}