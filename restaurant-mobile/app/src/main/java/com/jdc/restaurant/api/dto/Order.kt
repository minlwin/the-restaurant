package com.jdc.restaurant.api.dto

import java.util.*

data class Order(
    var id:Int = 0,
    var product: Product? = null,
    var unitPrice:Int = 0,
    var quantity:Int = 0,
    var status:String? = null,
    var remind:Int = 0,
    var orderTime:Date? = null
)