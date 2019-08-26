package com.suarezgustavo.rappichallenge.model.network.response

import com.google.gson.annotations.SerializedName

data class User(

    val name: String,
    @SerializedName("zomato_handle") val zomatoHandle: String,
    @SerializedName("foodie_level") val foodieLevel: String,
    @SerializedName("foodie_level_num") val foodieLevelNum: Int,
    @SerializedName("foodie_color") val foodieColor: String,
    @SerializedName("profile_url") val profileUrl: String,
    @SerializedName("profile_deeplink") val profileDeeplink: String,
    @SerializedName("profile_image") val profileImage: String
)