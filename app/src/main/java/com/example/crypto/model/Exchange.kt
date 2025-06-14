package com.example.crypto.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Exchange(
    @Json(name ="exchangeId")
    val exchangeId: String? = null,
    @Json(name ="exchangeUrl")
    val exchangeUrl: String? = null,
    @Json(name ="name")
    val name: String? = null,
    @Json(name ="percentTotalVolume")
    val percentTotalVolume: String? = null,
    @Json(name ="rank")
    val rank: String? = null,
    @Json(name ="socket")
    val socket: Boolean? = null,
    @Json(name ="tradingPairs")
    val tradingPairs: String? = null,
    @Json(name ="updated")
    val updated: Long? = null,
    @Json(name ="volumeUsd")
    val volumeUsd: String? = null
)