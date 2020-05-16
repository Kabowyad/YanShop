package com.example.yanshop.presenter

import com.example.yanshop.data.BasketProductDao
import com.example.yanshop.domain.model.Basket
import com.example.yanshop.domain.model.Product
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class BasketPresenter @Inject constructor(
    private val basketProductDao: BasketProductDao
) : BasePresenter<BasketView>() {
    private val basket = Basket(basketProductDao.getAllProducts().toMutableList())

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setItems()
        viewState.setOrderBtnEnabledStatus(basket.products.isNotEmpty())
    }

    fun setItems() = viewState.setItems(basket.products)

    fun passBasketToOrder() = viewState.openBasketOrder(basket)

    fun removeItem(product: Product) {
        basketProductDao.removeProduct(product)
        val position = basket.products.indexOf(product)
        basket.products.removeAt(position)
        viewState.removeItem(position)
        viewState.setOrderBtnEnabledStatus(basket.products.isNotEmpty())
    }
}