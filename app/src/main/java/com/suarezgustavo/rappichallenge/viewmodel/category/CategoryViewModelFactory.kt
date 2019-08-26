package com.suarezgustavo.rappichallenge.viewmodel.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suarezgustavo.rappichallenge.model.repository.RCRepository

class CategoryViewModelFactory(
    private val rcRepository: RCRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryViewModel(rcRepository) as T
    }
}