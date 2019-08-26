package com.suarezgustavo.rappichallenge.model.repository

import androidx.lifecycle.LiveData
import com.suarezgustavo.rappichallenge.model.entity.Category

interface RCRepository {

    suspend fun getAllCategories(): LiveData<List<Category>>?
}