package com.suarezgustavo.rappichallenge.data.model.entity

import com.google.gson.annotations.SerializedName

data class Review(

    val rating: Int,
    @SerializedName("review_text") val reviewText: String,
    val id: Int,
    @SerializedName("rating_color") val ratingColor: String,
    @SerializedName("review_time_friendly") val reviewTimeFriendly: String,
    @SerializedName("rating_text") val ratingText: String,
    val timestamp: Int,
    val likes: Int,
    val user: User,
    @SerializedName("comments_count") val commentsCount: Int
)