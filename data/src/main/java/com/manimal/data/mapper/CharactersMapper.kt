package com.manimal.data.mapper

import com.manimal.data.model.response.CharactersListResponseModel
import com.manimal.data.model.response.CharactersListResultsModel
import com.manimal.domain.model.CharacterData
import com.manimal.domain.model.CharactersListData

fun CharactersListResponseModel.mapToCharactersListData(): CharactersListData {
    return CharactersListData(
        total = this.data?.total,
        count = this.data?.count,
        results = getCharactersList(this.data?.results)
    )
}

private fun getCharactersList(results: List<CharactersListResultsModel>?): List<CharacterData> {
    results?.let {
        var list: MutableList<CharacterData> = mutableListOf()
        it.forEach { item ->
            list.add(
                CharacterData(
                    id = item.id,
                    name = item.name,
                    description = item.description,
                    modified = item.modified,
                    resourceURI = item.resourceURI
                )
            )
        }
    }

    return mutableListOf()
}
