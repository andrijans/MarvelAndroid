package com.andrijans.marveltest.presentation.common.util

import android.content.Context
import android.net.ConnectivityManager
import java.io.File

class NetworkUtil(val context: Context) {

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun getCacheDir(): File {
        return context.cacheDir
    }
}