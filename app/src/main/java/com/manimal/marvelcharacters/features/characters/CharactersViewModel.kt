package com.manimal.marvelcharacters.features.characters

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
    val characterDetailLiveData: MutableLiveData<CharactersListDataContainer> = MutableLiveData()

    fun getCharactersList(networkNotConnected: Boolean) {
        charactersListLiveData.value = CharactersListDataContainer(BaseStatus.LOADING)

        if (networkNotConnected) {
            charactersListLiveData.value = CharactersListDataContainer(BaseStatus.ERROR_CONNECTION)
            return
        }

        viewModelScope.launch {
            when (val response = charactersUseCase.getCharactersList()) {
                is UseCaseResult.Success -> charactersListLiveData.value = CharactersListDataContainer(BaseStatus.SUCCESS, response.data)
                is UseCaseResult.Failure -> charactersListLiveData.value = CharactersListDataContainer(BaseStatus.FAILED, response.errorModel)
            }
        }
    }

    fun getCharacterDetail(networkNotConnected: Boolean, characterId: Int) {
        characterDetailLiveData.value = CharactersListDataContainer(BaseStatus.LOADING)

        if (networkNotConnected) {
            characterDetailLiveData.value = CharactersListDataContainer(BaseStatus.ERROR_CONNECTION)
            return
        }

        viewModelScope.launch {
            when (val response = charactersUseCase.getCharacterDetail(characterId)) {
                is UseCaseResult.Success -> characterDetailLiveData.value = CharactersListDataContainer(BaseStatus.SUCCESS, response.data)
                is UseCaseResult.Failure -> characterDetailLiveData.value = CharactersListDataContainer(BaseStatus.FAILED, response.errorModel)
            }
        }
    }
}
