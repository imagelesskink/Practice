package com.example.practice.networking

import com.example.practice.model.ProductsResponse
import retrofit2.http.GET

interface ProductService {
    @GET(PRODUCTS_ENDPOINT)
    suspend fun getProducts() : ProductsResponse
}