package com.example.yanshop.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface OrderView : MvpView {
    fun printTotal(msg: String)
    fun showErrorFirstName(visible: Boolean)
    fun showErrorLastName(visible: Boolean)
    fun showErrorFatherName(visible: Boolean)
    fun showErrorPhoneNumber(visible: Boolean)
}
