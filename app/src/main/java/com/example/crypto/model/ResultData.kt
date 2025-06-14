package com.example.crypto.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultData(
    @Json(name = "data")
    val `data`: List<Data>? = null,
    @Json(name ="timestamp")
    val timestamp: Long? = null
)