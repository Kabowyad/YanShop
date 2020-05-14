package com.example.yanshop.presenter

import com.example.yanshop.data.ViewedProductDaoImpl
import com.example.yanshop.domain.MainApi
import com.example.yanshop.domain.model.Product
import kotlinx.coroutines.launch
import moxy.InjectViewState

@InjectViewState
class CatalogPresenter(
    private val api: MainApi,
    private val dao: ViewedProductDaoImpl
) : CoroutinePresenter<CatalogView>() {
    var products = listOf(
        Product("Applejack", 130.0),
        Product("Pinkie Pie", 150.0, 20),
        Product("Rarity", 115.0)
    )


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            val remoteProducts = api.allProducts()
            products = remoteProducts.mapNotNull { r ->
                try {
                    Product(r.name, r.price, r.discount, r.description, r.imageUrl, r.id)
                } catch (e: VerifyError) {
                    null
                }
            }
            viewState.setItems(products)
        }
    }

    public fun addProduct(product: Product) {
        dao.addProduct(product.id.toLong())
    }

    public fun addObject(product: Product) {
        dao.save(product)
    }
}