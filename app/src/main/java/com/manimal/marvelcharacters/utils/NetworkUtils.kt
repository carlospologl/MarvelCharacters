package com.manimal.marvelcharacters.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

    private fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun isNetworkNotConnected(context: Context): Boolean {
        return !isNetworkConnected(context)
    }
}
