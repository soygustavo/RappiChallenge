package com.suarezgustavo.rappichallenge.data.model.entity

import com.google.gson.annotations.SerializedName

data class UserRating(

    @SerializedName("aggregate_rating") val aggregateRating: Double,
    @SerializedName("rating_text") val ratingText: String,
    @SerializedName("rating_color") val ratingColor: String,
    val votes: Int
)