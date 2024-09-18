package com.example.practice.model

sealed class NetworkStateResponse {
    data class Success(val productsResponse: ProductsResponse): NetworkStateResponse()
    data class Failure(val error: String): NetworkStateResponse()
    data object Loading: NetworkStateResponse()
}