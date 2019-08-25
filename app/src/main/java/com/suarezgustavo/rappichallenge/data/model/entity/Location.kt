package com.suarezgustavo.rappichallenge.data.model.entity

import com.google.gson.annotations.SerializedName

data class Location(

    val address: String,
    val locality: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val zipcode: Int,
    @SerializedName("country_id") val countryId: Int
)