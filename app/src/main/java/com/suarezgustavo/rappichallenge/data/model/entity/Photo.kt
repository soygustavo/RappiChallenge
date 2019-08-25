package com.suarezgustavo.rappichallenge.data.model.entity

import com.google.gson.annotations.SerializedName

data class Photo(

    val id: String,
    val url: String,
    @SerializedName("thumb_url") val thumbUrl: String,
    val user: User,
    @SerializedName("res_id") val resId: Int,
    val caption: String,
    val timestamp: Int,
    @SerializedName("friendly_time") val friendlyTime: String,
    val width: Int,
    val height: Int,
    @SerializedName("comments_count") val commentsCount: Int,
    @SerializedName("likes_count") val likesCount: Int
)