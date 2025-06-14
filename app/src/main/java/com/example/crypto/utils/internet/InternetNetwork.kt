package com.example.crypto.utils.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class InternetNetwork {

    fun isConnect(context: Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetwork
        val currentNetwork = connectivityManager.getNetworkCapabilities(networkInfo) ?: return false
        return when{

            currentNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)   -> true
            currentNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            currentNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            else -> {false}
        }
    }
}