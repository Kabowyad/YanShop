package com.example.yanshop.ui

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yanshop.R
import com.example.yanshop.data.ViewedProductDaoImpl
import com.example.yanshop.domain.MainApi
import com.example.yanshop.domain.model.Product
import com.example.yanshop.presenter.CatalogPresenter
import com.example.yanshop.presenter.CatalogView
import com.example.yanshop.ui.ProductInfoActivity.Companion.PRODUCT_TAG
import kotlinx.android.synthetic.main.activity_catalog.*
import kotlinx.android.synthetic.main.activity_catalog.toolbar
import kotlinx.android.synthetic.main.toolbar_layout.view.*
import moxy.ktx.moxyPresenter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatalogActivity : BaseActivity(), CatalogView {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://207.254.71.167:9191/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api = retrofit.create(MainApi::class.java)
    private val presenter by moxyPresenter { CatalogPresenter(api, ViewedProductDaoImpl(sharedPreferences)) }
    private val adapter = CatalogAdapter ({openProductInfo(it)}, {addProductFromBasket(it)})

    override fun setItems(products: List<Product>) = adapter.setItems(products)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)
        setListeners()
        catalogRv.layoutManager = LinearLayoutManager(this)
        catalogRv.adapter = adapter
        toolbar.headerText.text = getString(R.string.headerCatalog)
    }

    private fun setListeners() {
        buttonToBasket.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openProductInfo(product: Product) = startActivity(
        Intent(this, ProductInfoActivity::class.java)
            .apply { putExtra(PRODUCT_TAG, product) })

    private fun addProductFromBasket(product: Product) = presenter.addObject(product)
}
