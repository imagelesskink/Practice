package com.example.practice.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.R
import com.example.practice.model.Product
import com.example.practice.viewmodel.ProductDetailsViewModel
import com.example.practice.viewmodel.ProductDetailsViewModelFactory

class ProductDetailsActivity: AppCompatActivity() {
    private val productDetailsViewModel by viewModels<ProductDetailsViewModel> {
        ProductDetailsViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail_activity)

        var currentProduct: Product? = null

        val title: TextView = findViewById(R.id.title)
        val brand: TextView = findViewById(R.id.brand)
        val description: TextView = findViewById(R.id.description)
        val availability: TextView = findViewById(R.id.availability)
        val price: TextView = findViewById(R.id.price)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentProduct = bundle.getSerializable("product") as Product
            productDetailsViewModel.setProduct(currentProduct)
        }
        title.text = productDetailsViewModel.getProduct()?.title
        brand.text = productDetailsViewModel.getProduct()?.brand
        description.text = productDetailsViewModel.getProduct()?.description
        availability.text = "Currently " + productDetailsViewModel.getProduct()?.availabilityStatus
        price.text = "$" + productDetailsViewModel.getProduct()?.price.toString()
    }
}