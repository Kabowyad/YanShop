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
import kotlinx.android.synthetic.main.product_item.*

class BasketAdapter(
    private val onDelete: (string: String) -> Unit,
    private val openProductInfo: (product: Product) -> Unit
) : RecyclerView.Adapter<BasketAdapter.ViewHolder>() {
    private var products: List<Product> = listOf()

    override fun getItemCount(): Int = products.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.product_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    fun setItems(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
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
            basketDeleteIb.setOnClickListener { onDelete(product.id) }
        }
    }
}
