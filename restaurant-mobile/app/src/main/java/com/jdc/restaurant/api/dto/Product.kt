package com.jdc.restaurant.api.dto

data class Product(
    var id:Int,
    var code:String,
    var name:String,
    var category:Category?,
    var price:Int,
    var image:String?
)