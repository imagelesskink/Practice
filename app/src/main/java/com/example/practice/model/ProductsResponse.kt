package com.example.practice.model


import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("limit")
    val limit: Int? = 0,
    @SerializedName("products")
    val products: List<Product?>? = listOf(),
    @SerializedName("skip")
    val skip: Int? = 0,
    @SerializedName("total")
    val total: Int? = 0
)