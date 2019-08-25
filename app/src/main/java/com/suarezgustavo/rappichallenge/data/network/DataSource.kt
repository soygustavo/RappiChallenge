package com.suarezgustavo.rappichallenge.data.network

import androidx.lifecycle.LiveData
import com.suarezgustavo.rappichallenge.data.entity.SearchResult
import com.suarezgustavo.rappichallenge.data.entity.category.Categories

interface DataSource {
    val searchResult: LiveData<SearchResult>
    val categories: LiveData<Categories>

    suspend fun fetchSearchResult(idCategory: Int, start: Int)

    suspend fun fetchCategories()
}