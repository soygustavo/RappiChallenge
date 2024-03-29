package com.suarezgustavo

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.suarezgustavo.rappichallenge.model.database.RCDataBase
import com.suarezgustavo.rappichallenge.model.network.DataSourceImpl
import com.suarezgustavo.rappichallenge.model.network.api.ApiService
import com.suarezgustavo.rappichallenge.model.network.interceptors.ConectivityInterceptor
import com.suarezgustavo.rappichallenge.model.network.interceptors.ConectivityInterceptorImpl
import com.suarezgustavo.rappichallenge.model.repository.RCRepository
import com.suarezgustavo.rappichallenge.model.repository.RCRepositoryImpl
import com.suarezgustavo.rappichallenge.viewmodel.category.CategoryViewModelFactory
import com.suarezgustavo.rappichallenge.viewmodel.restaurant.RestaurantViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class RappiChallengeApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@RappiChallengeApplication))
        bind() from singleton { RCDataBase(instance()) }
        bind() from singleton { instance<RCDataBase>().categoryDAO() }
        bind() from singleton { instance<RCDataBase>().restaurantDAO() }
        bind<ConectivityInterceptor>() with singleton { ConectivityInterceptorImpl(instance()) }
        bind() from singleton { ApiService(instance()) }
        bind() from provider { DataSourceImpl(instance()) }
        bind<RCRepository>() with singleton { RCRepositoryImpl(instance(), instance(), instance()) }
        bind() from provider { CategoryViewModelFactory(instance()) }
        bind() from provider { RestaurantViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}