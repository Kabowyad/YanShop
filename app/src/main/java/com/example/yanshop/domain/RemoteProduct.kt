package com.example.yanshop.domain

data class RemoteProduct(
    val name: String,
    val price: Double,
    val discountPercent: Int,
    val description: String,
    val imageUrl: String,
    val attributes: List<Attribute>,
    val id: String
) {
    data class Attribute(
        val name: String,
        val value: String
    )
}