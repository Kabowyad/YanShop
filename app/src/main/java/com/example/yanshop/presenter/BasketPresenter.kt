package com.example.yanshop.presenter

import com.example.yanshop.domain.ViewedProductDao
import com.example.yanshop.domain.model.Basket
import com.example.yanshop.domain.model.Product
import moxy.InjectViewState

@InjectViewState
class BasketPresenter(
    private val viewedProductDao: ViewedProductDao
) : BasePresenter<BasketView>() {
    private val basket = Basket(
        mutableListOf(
            Product("Rainbow Dash", 100.0),
            Product("Twilight Sparkle", 120.0, 15)
        )
    )

    fun setItems()  {
        viewedProductDao.retriev().forEach {
            p -> basket1.products.add(p)
        }
        viewState.setItems(basket1.products)
    }

    private val basket1 = Basket(mutableListOf())

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setItems()
    }

    fun removeItem(id: String) {
        val product = basket.products.find { p -> p.id == id }
        val position = basket.products.indexOf(product)
        basket.products.removeAt(position)
        viewState.removeItem(position)
    }
}