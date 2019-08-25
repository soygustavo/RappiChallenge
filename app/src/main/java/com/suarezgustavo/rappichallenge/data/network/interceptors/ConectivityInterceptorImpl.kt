package com.suarezgustavo.rappichallenge.data.network.interceptors

import android.content.Context
import android.net.ConnectivityManager
import com.suarezgustavo.rappichallenge.internals.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConectivityInterceptorImpl(context: Context) :
    ConectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasConection()) throw NoConnectivityException()
        return chain.proceed((chain.request()))
    }

    private fun hasConection(): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}