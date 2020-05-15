package com.example.yanshop.ui

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yanshop.R
import com.example.yanshop.domain.model.Product
import com.example.yanshop.presenter.format
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.product_item.productTvName
import kotlinx.android.synthetic.main.product_item.productTvOldPrice
import kotlinx.android.synthetic.main.product_item.productTvPrice
import kotlinx.android.synthetic.main.product_item_in_catalog.*

class CatalogAdapter(
    private val openProductInfo: (Product) -> Unit,
    private val addProductToBasket: (Product) -> Unit
) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {
    private var catalogProducts: List<Product> = emptyList()

    override fun getItemCount(): Int = catalogProducts.size

    fun setItems(products: List<Product>) {
        this.catalogProducts = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.product_item_in_catalog, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(catalogProducts[position])
    }

    inner class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(product: Product) {
            productTvName.text = product.name
            if (product.discount > 0) {
                productTvOldPrice.text = format(product.price)
                productTvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

                productTvPrice.text = format(product.discountPrice)
                productTvPrice.setTextColor(Color.parseColor("#FFFF4444"))
                productTvPrice.setTypeface(null, Typeface.BOLD)
            } else {
                productTvOldPrice.text = ""
                productTvPrice.text = format(product.price)
            }
            productTvName.setOnClickListener { openProductInfo(product) }
            catalogAddIb.setOnClickListener { addProductToBasket(product) }
        }
    }
}