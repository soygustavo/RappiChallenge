package com.suarezgustavo.rappichallenge.model.repository

import androidx.lifecycle.LiveData
import com.suarezgustavo.rappichallenge.model.entity.Category
import com.suarezgustavo.rappichallenge.model.entity.Restaurant

interface RCRepository {

    suspend fun getAllCategories(): LiveData<List<Category>>

    suspend fun getRandomRestaurant(idCategory: Int): LiveData<Restaurant>
}