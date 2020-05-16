package com.example.yanshop.ui

import android.os.Bundle
import android.widget.EditText
import com.example.yanshop.TextChangeListener
import com.example.yanshop.R
import com.example.yanshop.domain.model.Basket
import com.example.yanshop.presenter.OrderPresenter
import com.example.yanshop.presenter.OrderView
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.activity_order.toolbar
import kotlinx.android.synthetic.main.toolbar_layout.view.*
import moxy.ktx.moxyPresenter

class OrderActivity : BaseActivity(), OrderView {
    private val presenter by moxyPresenter { OrderPresenter() }

    override fun printTotal(msg: String) {
        textViewOrderInfo.text = msg
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        val basket = intent?.getParcelableExtra(BASKET_TAG) ?: Basket(mutableListOf())
        presenter.setBasket(basket)
        toolbar.headerText.text = getString(R.string.headerOrder)
        setListeners()
    }

    override fun showErrorFirstName(visible: Boolean) = nameFirstField.showError(visible)
    override fun showErrorLastName(visible: Boolean) = nameLastField.showError(visible)
    override fun showErrorFatherName(visible: Boolean) = nameFatherField.showError(visible)
    override fun showErrorPhoneNumber(visible: Boolean) = phoneField.showError(visible)

    private fun setListeners() {
        nameFirstField.addTextChangedListener(TextChangeListener {
            presenter.setOrderFirstName(it.toString())
        })
        nameLastField.addTextChangedListener(TextChangeListener {
            presenter.setOrderLastName(it.toString())
        })
        nameFatherField.addTextChangedListener(TextChangeListener {
            presenter.setOrderFatherName(it.toString())
        })
        phoneField.addTextChangedListener(TextChangeListener {
            presenter.setOrderPhoneNumber(it.toString())
        })
        buttonOrderGoBack.setOnClickListener { finish() }
    }

    companion object {
        const val BASKET_TAG = "BASKET_TAG"
    }

}

fun EditText.showError(visible: Boolean) {
    val drawable = if (visible) R.drawable.ic_error_red_24dp else 0
    this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
}
