package com.example.practice.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R
import com.example.practice.model.Product

class ProductsAdapter(private val onClick: (Product) -> Unit):
    ListAdapter<Product,ProductsAdapter.ProductViewHolder>(ProductDiffCallback){
    class ProductViewHolder(itemView: View, val onClick: (Product) -> Unit):
        RecyclerView.ViewHolder(itemView) {
        private val nameView: TextView = itemView.findViewById(R.id.product_name)
        private val priceView: TextView = itemView.findViewById(R.id.product_price)
        private var currentProduct: Product? = null

        init {
            itemView.setOnClickListener {
                currentProduct?.let {
                    onClick(it)
                }
            }
        }

        fun bind(product: Product) {
            currentProduct = product

            nameView.text = product.title
            priceView.text = "$" + product.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }
}

object ProductDiffCallback: DiffUtil.ItemCallback<Product>() {
    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}