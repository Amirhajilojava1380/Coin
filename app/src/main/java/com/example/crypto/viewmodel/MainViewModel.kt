package com.example.crypto.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.data.repository.Repository
import com.example.crypto.model.ResultData
import com.example.crypto.model.ResultExchanges
import com.example.crypto.utils.internet.InternetNetwork
import com.example.crypto.utils.NetWorkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    @ApplicationContext private val context: Context
):ViewModel() {

    val readCryptoData :MutableLiveData<NetWorkResult<ResultData>> = MutableLiveData()

    fun getDataCrypto() = viewModelScope.launch{

        readCryptoData.value = NetWorkResult.Loading()

        if (InternetNetwork().isConnect(context)){

            try {

                val response = repository.remote.getCryptoData("e0ae0283359b705bc7bfc07dbb282761628dbfb779b9f61ced462826dabd29d5")
                readCryptoData.value = checkData(response)


            }catch (e:Exception){ Log.d("viewmodel" , e.message.toString())}


        }else{
            readCryptoData.value = NetWorkResult.Error("No Internet Connection")
        }




    }

    private fun checkData(response: Response<ResultData>): NetWorkResult<ResultData> {
        return when{

            response.code() == 408  ->{NetWorkResult.Error("REQUEST_TIMEOUT")}
            response.code() == 429  ->{NetWorkResult.Error("TOO_MANY_REQUESTS")}
            response.code() == 500  ->{NetWorkResult.Error("SERVER_ERROR")}
            response.isSuccessful -> {
                NetWorkResult.Success(response.body()!!)
            }
            //response.body()!!.data!!.isEmpty() -> {NetWorkResult.Error("NotData")}

            else -> {NetWorkResult.Error(response.message())}
        }
    }


    val readExchangesData :MutableLiveData<NetWorkResult<ResultExchanges>> = MutableLiveData()

    fun getExchangesData () = viewModelScope.launch {

        readExchangesData.value = NetWorkResult.Loading()

        if (InternetNetwork().isConnect(context)){

            try {

                val response = repository.remote.getExchangesData("e0ae0283359b705bc7bfc07dbb282761628dbfb779b9f61ced462826dabd29d5")
                readExchangesData.value = checkExchangesData(response)


            }catch (e:Exception){ Log.d("viewmodel" , e.message.toString())}


        }else{
            readExchangesData.value = NetWorkResult.Error("No Internet Connection")
        }

    }


    private fun checkExchangesData(response: Response<ResultExchanges>): NetWorkResult<ResultExchanges> {
        return when{

            response.code() == 408  ->{NetWorkResult.Error("REQUEST_TIMEOUT")}
            response.code() == 429  ->{NetWorkResult.Error("TOO_MANY_REQUESTS")}
            response.code() == 500  ->{NetWorkResult.Error("SERVER_ERROR")}
            response.isSuccessful -> {
                NetWorkResult.Success(response.body()!!)
            }

            else -> {NetWorkResult.Error(response.message())}
        }
    }



}