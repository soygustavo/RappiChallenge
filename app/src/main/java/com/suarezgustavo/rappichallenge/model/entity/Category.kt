package com.suarezgustavo.rappichallenge.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String
)