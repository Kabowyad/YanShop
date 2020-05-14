package com.example.yanshop.domain

import com.example.yanshop.domain.model.Product

interface ViewedProductDao {
    fun addProduct(productId: Long)
    fun getAllProducts(): List<Long>
    fun retriev(): List<Product>
}
