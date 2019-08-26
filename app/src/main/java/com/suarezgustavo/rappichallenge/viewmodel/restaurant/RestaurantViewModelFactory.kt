package com.suarezgustavo.rappichallenge.viewmodel.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suarezgustavo.rappichallenge.model.repository.RCRepository

class RestaurantViewModelFactory(
    private val rcRepository: RCRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RestaurantViewModel(rcRepository) as T
    }
}