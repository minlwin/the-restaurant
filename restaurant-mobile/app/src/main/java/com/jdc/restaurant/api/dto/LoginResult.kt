package com.jdc.restaurant.api.dto

data class LoginResult(
    var user:Employee? = null,
    var token:String? = null
)