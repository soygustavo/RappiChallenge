package com.suarezgustavo.rappichallenge.model.network

import androidx.lifecycle.LiveData
import com.suarezgustavo.rappichallenge.model.network.response.SearchResult
import com.suarezgustavo.rappichallenge.model.network.response.category.Categories

interface DataSource {
    val searchResult: LiveData<SearchResult>
    val categories: LiveData<Categories>

    suspend fun fetchSearchResult(idCategory: Int, start: Int)

    suspend fun fetchCategories()
}