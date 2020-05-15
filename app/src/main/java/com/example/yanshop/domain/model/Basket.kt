package com.example.yanshop.domain.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Basket(
    val products: MutableList<Product> = mutableListOf()
) : Parcelable{
    fun getPrice(): Double = products.map { product -> product.price }.sum()
    fun getDiscountPrice(): Double = products.map { product -> product.discountPrice }.sum()
}
