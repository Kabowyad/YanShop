package com.example.yanshop.presenter

import com.example.yanshop.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BasketView : MvpView {
    fun setItems(products: List<Product>)
    fun removeItem(position: Int)
}