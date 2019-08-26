package com.suarezgustavo.rappichallenge.model.repository

import androidx.lifecycle.LiveData
import com.suarezgustavo.rappichallenge.model.dao.CategoryDAO
import com.suarezgustavo.rappichallenge.model.dao.RestaurantDAO
import com.suarezgustavo.rappichallenge.model.entity.Category
import com.suarezgustavo.rappichallenge.model.entity.Restaurant
import com.suarezgustavo.rappichallenge.model.network.DataSourceImpl
import com.suarezgustavo.rappichallenge.model.network.response.category.ItemCategory
import com.suarezgustavo.rappichallenge.model.network.response.restaurant.Restaurants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class RCRepositoryImpl(
    private val categoryDAO: CategoryDAO,
    private val restaurantDAO: RestaurantDAO,
    private val dataSourceImpl: DataSourceImpl
) : RCRepository {

    companion object {

        private const val cacheTime = 30L
        private var cacheDuration = ZonedDateTime.now().plusMinutes(cacheTime)
        private var currentFetchTime = ZonedDateTime.now().plusMinutes(cacheTime + 1)
    }

    init {
        dataSourceImpl.categories.observeForever { newListCategories ->
            persistFetchedCategories(newListCategories.categories)
        }
        dataSourceImpl.searchResult.observeForever { newListSearchResult ->
            persistFetchedRestaurants(newListSearchResult.restaurantList)
        }
    }


    override suspend fun getAllCategories(): LiveData<List<Category>> {
        initCategoryData()
        return withContext(Dispatchers.IO) {
            return@withContext categoryDAO.getAllCategories()
        }
    }

    override suspend fun getRandomRestaurant(idCategory: Int): LiveData<Restaurant> {
        fetchRandomRestaurantFromCategory(idCategory)
        return withContext(Dispatchers.IO) {
            return@withContext restaurantDAO.getLast()
        }
    }


    private fun persistFetchedCategories(fetchedCategories: List<ItemCategory>) {
        GlobalScope.launch(Dispatchers.IO) {
            for (item in fetchedCategories) {
                categoryDAO.insertUpdate(item.category)
            }
        }
    }

    private fun persistFetchedRestaurants(restaurantList: List<Restaurants>) {
        GlobalScope.launch(Dispatchers.IO) {
            for (item in restaurantList) {
                restaurantDAO.insertUpdate(item.restaurant)
            }
        }
    }


    private suspend fun initCategoryData() {
        if (!isValidCache(currentFetchTime)) {
            fetchCategories()
            cacheDuration = ZonedDateTime.now()
        }
    }

    private suspend fun fetchCategories() {
        dataSourceImpl.fetchCategories()
    }

    private suspend fun fetchRandomRestaurantFromCategory(idCategory: Int) {
        val rndNumber = (0..99).random()
        dataSourceImpl.fetchSearchResult(idCategory, rndNumber)
    }

    private fun isValidCache(fetchTime: ZonedDateTime): Boolean {
        when (fetchTime.isBefore(cacheDuration)) {
            true -> return true
            false -> {
                currentFetchTime = ZonedDateTime.now()
                cacheDuration = ZonedDateTime.now().plusMinutes(cacheTime)
                return false
            }
        }
    }
}