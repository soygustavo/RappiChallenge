package com.suarezgustavo.rappichallenge.model.repository

import androidx.lifecycle.LiveData
import com.suarezgustavo.rappichallenge.model.dao.CategoryDAO
import com.suarezgustavo.rappichallenge.model.entity.Category
import com.suarezgustavo.rappichallenge.model.network.DataSourceImpl
import com.suarezgustavo.rappichallenge.model.network.response.category.ItemCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class RCRepositoryImpl(
    private val categoryDAO: CategoryDAO,
    private val dataSourceImpl: DataSourceImpl
) : RCRepository {

    companion object {

        private const val cacheTime = 30L
        private var cacheDuration = ZonedDateTime.now().plusMinutes(cacheTime)
    }

    init {
        dataSourceImpl.categories.observeForever { newListCategories ->
            persistFetchedCategories(newListCategories.categories)
        }
    }

    override suspend fun getAllCategories(): LiveData<List<Category>>? {
        return withContext(Dispatchers.IO) {
            when (isValidCache(ZonedDateTime.now())) {
                true -> return@withContext categoryDAO.getAllCategories()
                false -> return@withContext null
            }

        }
    }

    private fun persistFetchedCategories(fetchedCategories: List<ItemCategory>) {
        GlobalScope.launch(Dispatchers.IO) {
            for (item in fetchedCategories) {
                categoryDAO.insertUpdate(item.category)
            }
        }
    }


    private suspend fun initCategoryData() {
        if (getAllCategories() == null) {
            fetchCategories()
        }
    }

    private suspend fun fetchCategories() {
        dataSourceImpl.fetchCategories()
    }

    private fun isValidCache(fetchTime: ZonedDateTime): Boolean {
        when (fetchTime.isBefore(cacheDuration)) {
            true -> return true
            false -> {
                cacheDuration = ZonedDateTime.now().plusMinutes(cacheTime)
                return false
            }
        }
//        if (fetchTime.isBefore(cacheDuration)) {
//            return true
//        } else {
//            cacheDuration = ZonedDateTime.now().plusMinutes(cacheTime)
//            return false
//        }
    }
}