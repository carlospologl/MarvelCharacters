package com.manimal.marvelcharacters.utils

import androidx.annotation.StringRes

data class DialogWrapper(
    @StringRes var title: Int,
    @StringRes var message: Int,
    @StringRes var positiveMessage: Int? = null,
    @StringRes var negativeMessage: Int? = null,
    var positiveAction: () -> Unit = {},
    var negativeAction: () -> Unit = {}
)

