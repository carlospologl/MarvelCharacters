package com.manimal.data.mapper

import com.manimal.data.model.response.CharactersListResponseModel
import com.manimal.data.model.response.CharactersListResultsModel
import com.manimal.domain.model.CharacterData
import com.manimal.domain.model.CharactersListData
import com.manimal.domain.utils.Constants.DOT
import com.manimal.domain.utils.Constants.IMAGE_VARIANT_PORTRAIT_MEDIUM
import com.manimal.domain.utils.Constants.SLASH

fun CharactersListResponseModel.mapToCharactersListData(): CharactersListData {
    return CharactersListData(
        total = this.data?.total,
        count = this.data?.count,
        results = getCharactersList(this.data?.results)
    )
}

private fun getCharactersList(results: List<CharactersListResultsModel>?): List<CharacterData> {
    results?.let {
        val list: MutableList<CharacterData> = mutableListOf()
        it.forEach { item ->
            val comics = item.comics?.items?.map { comic -> comic.name ?: ""  }

            list.add(
                CharacterData(
                    id = item.id,
                    name = item.name,
                    description = item.description,
                    modified = item.modified,
                    resourceURI = item.resourceURI,
                    thumbnail =
                        item.thumbnail?.path +
                        SLASH +
                        IMAGE_VARIANT_PORTRAIT_MEDIUM +
                        DOT +
                        item.thumbnail?.extension,
                    comics = comics
                )
            )
        }
        return list
    }

    return mutableListOf()
}
