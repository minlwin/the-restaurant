package com.jdc.restaurant.api.dto

import java.io.Serializable

data class Table(
    var id:Int = 0,
    var tableNumber:String = "",
    var seats:Int = 0
):Serializable