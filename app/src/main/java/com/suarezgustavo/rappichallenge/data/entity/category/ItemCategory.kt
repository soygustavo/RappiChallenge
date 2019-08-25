package com.suarezgustavo.rappichallenge.data.entity.category

import com.google.gson.annotations.SerializedName

data class ItemCategory(
    @SerializedName("categories") val category: Category
)