package com.example.practice.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R
import com.example.practice.model.NetworkStateResponse
import com.example.practice.model.Product
import com.example.practice.viewmodel.ProductListViewModel
import com.example.practice.viewmodel.ProductListViewModelFactory

class MainActivity : AppCompatActivity() {
    private val productListViewModel: ProductListViewModel by viewModels<ProductListViewModel> {
        ProductListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val productsAdapter = ProductsAdapter { product ->  adapterOnClick(product)}

        val recyclerView: RecyclerView = findViewById(R.id.product_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productsAdapter
        val progressBar: ProgressBar = findViewById(R.id.progressBar_cyclic)

        productListViewModel.networkResponse.observe(this) {
            when(it) {
                is NetworkStateResponse.Failure -> {
                    Log.d("MainActivity","Network Error")
                }
                is NetworkStateResponse.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is NetworkStateResponse.Success -> {
                    progressBar.visibility = View.GONE
                    productsAdapter.submitList(it.productsResponse.products as MutableList<Product>)
                }
            }
        }

        productListViewModel.makeProductsRequest()
    }

    private fun adapterOnClick(product: Product) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }
}