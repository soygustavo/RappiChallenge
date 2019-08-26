package com.suarezgustavo.rappichallenge.viewmodel.restaurant

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suarezgustavo.rappichallenge.internals.lazyDeferred
import com.suarezgustavo.rappichallenge.model.repository.RCRepository

class RestaurantViewModel(
    private val rcRepository: RCRepository
) : ViewModel() {

    val idCategory: MutableLiveData<Int> = MutableLiveData()

    val restaurant by lazyDeferred {
        rcRepository.getRandomRestaurant(idCategory.value!!)
    }

    init {
        idCategory.value = 0
    }

    fun setMode(m: Int) {
        idCategory.value = m
    }

}