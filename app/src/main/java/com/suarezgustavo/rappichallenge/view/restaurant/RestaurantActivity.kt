package com.suarezgustavo.rappichallenge.view.restaurant

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.suarezgustavo.rappichallenge.R
import com.suarezgustavo.rappichallenge.view.base.ScopedActivity
import com.suarezgustavo.rappichallenge.viewmodel.restaurant.RestaurantViewModel
import com.suarezgustavo.rappichallenge.viewmodel.restaurant.RestaurantViewModelFactory
import kotlinx.android.synthetic.main.activity_restaurant.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class RestaurantActivity : ScopedActivity(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: RestaurantViewModelFactory by instance()

    private lateinit var viewModel: RestaurantViewModel

    private var idCategory: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        idCategory = intent.getIntExtra("keyIdentifier", 0)
    }

    override fun onResume() {
        super.onResume()
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(RestaurantViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        viewModel.idCategory.postValue(idCategory)
        val restaurant = viewModel.restaurant.await()
        restaurant.observe(this@RestaurantActivity, Observer {

            if (it == null) return@Observer

            webview!!.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(url)
                    return true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progress.visibility = View.GONE
                }
            }
            webview!!.clearHistory()
            webview!!.loadUrl(it.url)
        })
    }
}
