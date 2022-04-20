package com.manimal.marvelcharacters.utils

import androidx.annotation.StringRes

data class DialogWrapper(
    @StringRes var title: Int,
    @StringRes var message: Int,
    var stringMessage: String? = null,
    @StringRes var positiveMessage: Int? = null,
    @StringRes var negativeMessage: Int? = null,
    var positiveAction: () -> Unit = {},
    var negativeAction: () -> Unit = {}
)

