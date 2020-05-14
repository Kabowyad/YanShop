package com.example.yanshop.domain.model

data class Order(
    var firstName: String = "",
    var lastName: String = "",
    var fatherName: String = "",
    var phoneNumber: String = "",
    var payment: Payment = Payment.CARD
)
