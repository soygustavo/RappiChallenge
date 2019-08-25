package com.suarezgustavo.rappichallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.suarezgustavo.rappichallenge.R
import com.suarezgustavo.rappichallenge.data.network.DataSourceImpl
import com.suarezgustavo.rappichallenge.data.network.api.ApiService
import com.suarezgustavo.rappichallenge.data.network.interceptors.ConectivityInterceptorImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val apiService = ApiService(
            ConectivityInterceptorImpl(
                this.applicationContext!!
            )
        )

        val dataSource = DataSourceImpl(apiService)

        dataSource.categories.observe(this, Observer {
            testView.text = it.categories.toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
            this.coroutineContext
//            val searchResponse = apiService.getSearchResultAsync(3).await()
//            val categories = apiService.getCategoriesAsync().await()
            dataSource.fetchCategories()
        }
    }
}
