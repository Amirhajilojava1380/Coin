package com.example.crypto.data.datasource

import com.example.crypto.data.api.CryptoApi
import com.example.crypto.model.ResultData
import com.example.crypto.model.ResultExchanges
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource  @Inject constructor(
    private val api: CryptoApi
){

    suspend fun getCryptoData(apiKey:String): Response<ResultData> {
        return api.getCryptoData(apiKey)
    }

    suspend fun getExchangesData(apiKey:String):Response<ResultExchanges>{
        return api.getExchangesData(apiKey)
    }

}