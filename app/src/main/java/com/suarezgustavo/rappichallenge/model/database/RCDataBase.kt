package com.suarezgustavo.rappichallenge.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.suarezgustavo.rappichallenge.model.dao.CategoryDAO
import com.suarezgustavo.rappichallenge.model.dao.RestaurantDAO
import com.suarezgustavo.rappichallenge.model.entity.Category
import com.suarezgustavo.rappichallenge.model.entity.Restaurant

@Database(entities = [Category::class, Restaurant::class], version = 1)
abstract class RCDataBase : RoomDatabase() {

    abstract fun categoryDAO(): CategoryDAO
    abstract fun restaurantDAO(): RestaurantDAO

    companion object {
        private var instance: RCDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDB(context).also { instance = it }
        }

        private fun buildDB(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RCDataBase::class.java, "rappichallenge.db"
        ).build()
    }
}