package com.suarezgustavo.rappichallenge.model.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.suarezgustavo.rappichallenge.internals.NoConnectivityException
import com.suarezgustavo.rappichallenge.model.network.api.ApiService
import com.suarezgustavo.rappichallenge.model.network.response.SearchResult
import com.suarezgustavo.rappichallenge.model.network.response.category.Categories

class DataSourceImpl(private val apiService: ApiService) : DataSource {

    private val mSearchResult = MutableLiveData<SearchResult>()
    private val mCategories = MutableLiveData<Categories>()

    override val searchResult: LiveData<SearchResult>
        get() = mSearchResult

    override val categories: LiveData<Categories>
        get() = mCategories

    override suspend fun fetchSearchResult(idCategory: Int, start: Int) {
        try {
            val fetchedData = apiService
                .getSearchResultAsync(idCategory, start)
                .await()
            mSearchResult.postValue(fetchedData)
        } catch (e: NoConnectivityException) {
            Log.d("Connection", "shoot", e)
        }
    }

    override suspend fun fetchCategories() {
        try {
            val fetchedData = apiService
                .getCategoriesAsync()
                .await()
            mCategories.postValue(fetchedData)
        } catch (e: NoConnectivityException) {
            Log.d("Conection", "shoot", e)
        }
    }
}