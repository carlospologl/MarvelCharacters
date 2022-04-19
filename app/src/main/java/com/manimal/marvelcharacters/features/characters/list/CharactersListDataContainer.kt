package com.manimal.marvelcharacters.features.characters.list

import com.manimal.domain.model.CharactersListData
import com.manimal.marvelcharacters.features.base.BaseStatus

class CharactersListDataContainer {
    var status: BaseStatus = BaseStatus.LOADING
    var data: CharactersListData? = null

    constructor(status: BaseStatus) {
        this.status = status
    }

    constructor(status: BaseStatus, data: CharactersListData) {
        this.status = status
        this.data = data
    }
}