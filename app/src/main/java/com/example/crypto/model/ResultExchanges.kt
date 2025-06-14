package com.example.crypto.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultExchanges(
    @Json(name ="data")
    val exchanges: List<Exchange>? = null,
    @Json(name ="timestamp")
    val timestamp: Long? = null
)