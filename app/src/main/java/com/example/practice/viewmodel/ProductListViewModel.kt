package com.example.practice.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.practice.model.NetworkStateResponse
import com.example.practice.model.ProductsResponse
import com.example.practice.networking.ProductApiClient
import com.example.practice.repository.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val repository: ProductRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {
    private val _networkResponse: MutableLiveData<NetworkStateResponse> by lazy {
        MutableLiveData<NetworkStateResponse>()
    }
    val networkResponse = _networkResponse
    var productsResponse: ProductsResponse? = null

    fun makeProductsRequest() {
        _networkResponse.value = NetworkStateResponse.Loading
        viewModelScope.launch {
            try {
                val response = repository.getProductResponse(dispatcher)
                _networkResponse.value = NetworkStateResponse.Success(response)
                productsResponse = response
            } catch (ex: Exception) {
                _networkResponse.value = NetworkStateResponse.Failure("Network Failed")
            }
        }
    }
}

class ProductListViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductListViewModel(ProductRepository(ProductApiClient())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}