package com.example.yanshop.domain

import com.example.yanshop.domain.model.Product

interface BasketProductDao {
    fun removeProduct(product: Product)
    fun addProduct(product: Product)
    fun getAllProducts(): List<Product>
}