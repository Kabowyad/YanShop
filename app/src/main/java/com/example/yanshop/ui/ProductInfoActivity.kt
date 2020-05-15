package com.example.yanshop.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.yanshop.R
import com.example.yanshop.domain.model.Product
import com.example.yanshop.presenter.ProductView
import com.example.yanshop.presenter.format
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.activity_product_info.*
import kotlinx.android.synthetic.main.activity_product_info.toolbar
import kotlinx.android.synthetic.main.toolbar_layout.view.*

class ProductInfoActivity : BaseActivity(), ProductView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_info)
        val product = intent?.getParcelableExtra<Product>(PRODUCT_TAG) ?: return
        setProductInfoLayout(product)
        toolbar.headerText.text = getString(R.string.headerProduct)
        setListeners()
    }

    override fun setProductInfoLayout(product: Product) {
        Glide
            .with(ivImage.context)
            .load(product.imageUrl)
            .error(R.mipmap.ic_launcher)
            .into(ivImage)
        tvName.text = product.name
        tvPrice.text = format(product.price)
        tvDiscount.text = product.discount.toString()
        tvPriceWithDiscount.text = format(product.discountPrice)
        tvDescription.text = product.description
    }

    private fun setListeners() {
        buttonProductGoBack.setOnClickListener { finish() }
    }

    companion object {
        const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}
