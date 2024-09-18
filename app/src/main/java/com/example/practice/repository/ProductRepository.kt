package com.example.practice.repository

import com.example.practice.model.ProductsResponse
import com.example.practice.networking.ProductApiClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ProductRepository(val productApiClient: ProductApiClient) {
    suspend fun getProductResponse(
        dispatcher: CoroutineDispatcher
    ): ProductsResponse {
        return withContext(dispatcher) {
            productApiClient.api.getProducts()
        }
    }
}