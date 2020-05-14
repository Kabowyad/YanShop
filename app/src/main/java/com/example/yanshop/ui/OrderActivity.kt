package com.example.yanshop.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.yanshop.R
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
        toolbar.headerText.text = getString(R.string.headerOrder)
        setListeners()
    }

    override fun showErrorFirstName(visible: Boolean) = nameFirstField.showError(visible)
    override fun showErrorLastName(visible: Boolean) = nameLastField.showError(visible)
    override fun showErrorFatherName(visible: Boolean) = nameFatherField.showError(visible)
    override fun showErrorPhoneNumber(visible: Boolean) = phoneField.showError(visible)

    private fun setListeners() {
        nameFirstField.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {
                presenter.setOrderFirstName(s.toString())
            }
        })
        nameLastField.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {
                presenter.setOrderLastName(s.toString())
            }
        })
        nameFatherField.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {
                presenter.setOrderFatherName(s.toString())
            }
        })
        phoneField.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {
                presenter.setOrderPhoneNumber(s.toString())
            }
        })
        buttonOrderGoBack.setOnClickListener { finish() }
    }
}

fun EditText.showError(visible: Boolean) {
    val drawable = if (visible) R.drawable.ic_error_red_24dp else 0
    this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
}
