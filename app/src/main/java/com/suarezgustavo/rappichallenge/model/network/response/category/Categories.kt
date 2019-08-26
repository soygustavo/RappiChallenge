package com.suarezgustavo.rappichallenge.model.network.response.category

import com.google.gson.annotations.SerializedName

class Categories(
    @SerializedName("categories") val categories: List<ItemCategory>
)