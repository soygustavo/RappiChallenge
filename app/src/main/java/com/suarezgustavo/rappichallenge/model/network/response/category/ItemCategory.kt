package com.suarezgustavo.rappichallenge.model.network.response.category

import com.google.gson.annotations.SerializedName
import com.suarezgustavo.rappichallenge.model.entity.Category

data class ItemCategory(
    @SerializedName("categories") val category: Category
)