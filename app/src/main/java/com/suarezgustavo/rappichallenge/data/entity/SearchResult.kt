package com.suarezgustavo.rappichallenge.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.suarezgustavo.rappichallenge.data.entity.restaurant.Restaurants

data class SearchResult(
    @Expose @SerializedName("results_found") val resultsFound: Int,
    @SerializedName("results_start") val resultsStart: Int,
    @SerializedName("results_shown") val resultsShown: Int,
    @SerializedName("restaurants") val restaurantList: List<Restaurants>
)