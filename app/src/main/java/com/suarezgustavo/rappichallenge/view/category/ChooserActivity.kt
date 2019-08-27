package com.suarezgustavo.rappichallenge.view.category

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.NumberPicker
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.suarezgustavo.rappichallenge.R
import com.suarezgustavo.rappichallenge.view.base.ScopedActivity
import com.suarezgustavo.rappichallenge.view.restaurant.RestaurantActivity
import com.suarezgustavo.rappichallenge.viewmodel.category.CategoryViewModel
import com.suarezgustavo.rappichallenge.viewmodel.category.CategoryViewModelFactory
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class ChooserActivity : ScopedActivity(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: CategoryViewModelFactory by instance()

    private lateinit var viewModel: CategoryViewModel

    var categoriesArray = arrayOfNulls<String>(0)

    val categoryTable = mutableMapOf<String, Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        setSupportActionBar(toolbar)
        setupUI()
    }

    override fun onResume() {
        super.onResume()
        number_picker_string.visibility = View.VISIBLE
        txt_test.visibility = View.GONE
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CategoryViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        val categories = viewModel.categories.await()
        categories.observe(this@ChooserActivity, Observer {

            if (it == null) return@Observer

            categoriesArray = arrayOfNulls(it.size)

            for (i in it.indices) {
                categoriesArray[i] = it[i].name
                categoryTable[it[i].name] = it[i].id
            }

            number_picker_string.minValue = 0
            number_picker_string.maxValue = categoriesArray.size - 1

            number_picker_string.displayedValues = categoriesArray


        })
    }

    private fun setupUI() {
        var value: String? = ""

        number_picker_string.setOnScrollListener { numberPicker, scrollState ->
            if (scrollState === NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                var newValue = categoriesArray[numberPicker.value]

                val r = Runnable {
                    value = categoriesArray[numberPicker.value]
                    if (value === newValue) {

                        numberPicker.visibility = View.GONE
                        txt_test.visibility = View.VISIBLE
                        txt_test.text = categoriesArray[numberPicker.value]

                        val intent = Intent(this@ChooserActivity, RestaurantActivity::class.java)
                        intent.putExtra(
                            "keyIdentifier",
                            categoryTable[categoriesArray[numberPicker.value]]
                        )
                        startActivity(intent)
                    }
                }
                Handler().postDelayed(r, 1000)

            }
        }
    }

}


