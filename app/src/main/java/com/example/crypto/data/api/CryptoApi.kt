package com.example.crypto.data.api

import com.example.crypto.model.ResultData
import com.example.crypto.model.ResultExchanges
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {

    @GET("assets")
    suspend fun getCryptoData(@Query("apiKey") apiKey:String):Response<ResultData>

    @GET("exchanges")
    suspend fun getExchangesData(@Query("apiKey") apiKey:String):Response<ResultExchanges>

}