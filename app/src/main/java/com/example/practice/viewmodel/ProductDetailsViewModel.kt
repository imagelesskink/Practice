package com.example.practice.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practice.model.Product
import java.lang.IllegalArgumentException

class ProductDetailsViewModel(): ViewModel() {
    private var product: Product? = null
    fun getProduct(): Product? {
        return product
    }

    fun setProduct(product: Product) {
        this.product = product
    }
}

class ProductDetailsViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductDetailsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}