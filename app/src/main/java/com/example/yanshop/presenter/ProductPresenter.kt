package com.example.yanshop.presenter

import com.example.yanshop.domain.model.Product
import moxy.InjectViewState
import kotlin.math.round

@InjectViewState
class ProductPresenter : BasePresenter<ProductView>() {
    var product: Product? = null
}

fun format(price: Double): String {
    val roundedPrice = round(100.0 * price) / 100.0
    val digits: Int = if (roundedPrice % 1 > 0) 2 else 0
    return "%.${digits}f".format(roundedPrice)
}
