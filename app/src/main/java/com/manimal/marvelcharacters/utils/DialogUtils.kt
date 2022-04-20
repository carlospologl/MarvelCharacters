package com.manimal.marvelcharacters.utils

import android.app.Activity
import androidx.appcompat.app.AlertDialog

object DialogUtils {

    fun createDialog(activity: Activity, wrapper: DialogWrapper) {
        val builder = AlertDialog.Builder(activity)

        builder.apply {
            setMessage(wrapper.message)
            setTitle(wrapper.title)
            wrapper.positiveMessage?.let { okMessage ->
                setPositiveButton(okMessage) { _, _ ->
                    wrapper.positiveAction.invoke()
                }
            }
            wrapper.negativeMessage?.let { okMessage ->
                setNegativeButton(okMessage) { _, _ ->
                    wrapper.negativeAction.invoke()
                }
            }
        }

        builder.create().show()
    }
}
