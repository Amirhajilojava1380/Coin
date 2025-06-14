package com.example.crypto.utils

sealed class NetWorkResult<out T>(data: T? = null , message: String? = null) {

    class Success<T>(val data: T) : NetWorkResult<T>(data)
    class Error  <T>(val message: String?) : NetWorkResult<T>(data = null,message)
    class Loading<T>: NetWorkResult<T>()

}