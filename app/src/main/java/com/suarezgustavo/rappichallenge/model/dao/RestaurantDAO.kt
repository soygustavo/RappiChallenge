package com.suarezgustavo.rappichallenge.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suarezgustavo.rappichallenge.model.entity.Restaurant

@Dao
interface RestaurantDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpdate(restaurant: Restaurant)

    @Query("SELECT * FROM restaurant_table WHERE id = :id")
    fun getRestaurantId(id: Int): LiveData<Restaurant>

    @Query("SELECT * FROM restaurant_table")
    fun getAll(): LiveData<List<Restaurant>>

    @Query("SELECT * FROM restaurant_table ORDER BY internalID DESC LIMIT 1")
    fun getLast(): LiveData<Restaurant>
}