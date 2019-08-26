package com.suarezgustavo.rappichallenge.viewmodel.category

import androidx.lifecycle.ViewModel
import com.suarezgustavo.rappichallenge.internals.lazyDeferred
import com.suarezgustavo.rappichallenge.model.repository.RCRepository

class CategoryViewModel(
    private val rcRepository: RCRepository
) : ViewModel() {

    val categories by lazyDeferred {
        rcRepository.getAllCategories()
    }
}