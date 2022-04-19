package com.manimal.marvelcharacters.features.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manimal.domain.response.UseCaseResult
import com.manimal.domain.usecase.CharactersUseCase
import com.manimal.marvelcharacters.features.base.BaseStatus
import com.manimal.marvelcharacters.features.characters.list.CharactersListDataContainer
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val charactersUseCase: CharactersUseCase
) : ViewModel() {

    val charactersListLiveData: MutableLiveData<CharactersListDataContainer> = MutableLiveData()

    fun getCharactersList() {
        viewModelScope.launch {
            when (val response = charactersUseCase.getCharactersList()) {
                is UseCaseResult.Success -> charactersListLiveData.value = CharactersListDataContainer(BaseStatus.SUCCESS, response.data)
                is UseCaseResult.Failure -> charactersListLiveData.value = CharactersListDataContainer(BaseStatus.FAILED)
            }
        }
    }
}
