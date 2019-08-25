package com.suarezgustavo.rappichallenge.data.network.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.suarezgustavo.rappichallenge.BuildConfig
import com.suarezgustavo.rappichallenge.data.entity.SearchResult
import com.suarezgustavo.rappichallenge.data.entity.category.Categories
import com.suarezgustavo.rappichallenge.data.network.interceptors.ConectivityInterceptor
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(END_POINT_SEARCH)
    fun getSearchResultAsync(
        @Query("category") idCategory: Int,
        @Query("start") start: Int = 5,
        @Query("count") count: Int = 1
    ): Deferred<SearchResult>

    @GET(END_POINT_CATEGORIES)
    fun getCategoriesAsync(): Deferred<Categories>

    companion object {

        //== ENDPOINTS
        const val END_POINT_SEARCH = "search"
        const val END_POINT_CATEGORIES = "categories"

        //== HEADERS
        private const val HEADER_USER_KEY = "user-key"


        operator fun invoke(connectivityInterceptor: ConectivityInterceptor): ApiService {

            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .addHeader(HEADER_USER_KEY, BuildConfig.ZomatoApiKey)
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.ZomatoBaseUrl)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}