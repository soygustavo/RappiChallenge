package com.suarezgustavo.rappichallenge.model.network.response

import com.google.gson.annotations.SerializedName

data class Location(

    val address: String,
    val locality: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val zipcode: String,
    @SerializedName("country_id") val countryId: Int
)