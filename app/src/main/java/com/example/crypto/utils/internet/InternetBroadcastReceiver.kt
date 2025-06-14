package com.example.crypto.utils.internet

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class InternetBroadcastReceiver(private val onInternetChange:(Boolean)-> Unit):BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {

        onInternetChange(InternetNetwork().isConnect(context))

    }
}