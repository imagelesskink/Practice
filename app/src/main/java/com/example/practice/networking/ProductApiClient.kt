package com.example.practice.networking

import android.util.Log
import com.example.practice.model.ProductsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductApiClient {
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor{
            Log.d("ProductAPIClient", it)
        }.setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    val api: ProductService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(ProductService::class.java)
}