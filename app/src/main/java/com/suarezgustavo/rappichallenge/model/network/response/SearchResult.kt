package com.suarezgustavo.rappichallenge.model.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.suarezgustavo.rappichallenge.model.network.response.restaurant.Restaurants

data class SearchResult(
    @Expose @SerializedName("results_found") val resultsFound: Int,
    @SerializedName("results_start") val resultsStart: Int,
    @SerializedName("results_shown") val resultsShown: Int,
    @SerializedName("restaurants") val restaurantList: List<Restaurants>
)