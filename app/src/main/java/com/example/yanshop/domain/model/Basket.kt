package com.example.yanshop.domain.model

class Basket(
    val products: MutableList<Product> = mutableListOf()
) {
    fun getPrice(): Double = products.map { product -> product.price }.sum()
    fun getDiscountPrice(): Double = products.map { product -> product.discountPrice }.sum()
}
