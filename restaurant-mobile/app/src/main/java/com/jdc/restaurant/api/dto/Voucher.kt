package com.jdc.restaurant.api.dto

import java.util.*

data class Voucher(
    var id:Int = 0,
    var tableNumber:String = "",
    var saleDate:Date? = null,
    var subTotal:Int = 0
)