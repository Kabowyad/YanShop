package com.example.yanshop.ui

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yanshop.R
import com.example.yanshop.data.ViewedProductDaoImpl
import com.example.yanshop.domain.model.Product
import com.example.yanshop.presenter.BasketPresenter
import com.example.yanshop.presenter.BasketView
import kotlinx.android.synthetic.main.activity_basket.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*
import moxy.ktx.moxyPresenter

class BasketActivity : BaseActivity(), BasketView {
    private val presenter by moxyPresenter {
        BasketPresenter(
            ViewedProductDaoImpl(sharedPreferences)
        )
    }
    private val adapter = BasketAdapter(
        { p -> presenter.removeItem(p) },
        { p -> openProductInfo(p) }
    )

    override fun removeItem(position: Int) = adapter.notifyItemRemoved(position)
    override fun setItems(products: List<Product>) = adapter.setItems(products)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
        basketRv.layoutManager = LinearLayoutManager(this)
        basketRv.adapter = adapter
        toolbar.headerText.text = getString(R.string.headerBasket)
        setListeners()
    }

    private fun setListeners() {
        buttonBasketGoBack.setOnClickListener { finish() }
        buttonMakeOrder.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
        }
    }

    private fun openProductInfo(product: Product) {
        startActivity(Intent(this, ProductInfoActivity::class.java)
            .apply { putExtra(ProductInfoActivity.PRODUCT_TAG, product) })
    }
}
