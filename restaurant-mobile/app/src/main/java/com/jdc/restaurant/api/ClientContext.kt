package com.jdc.restaurant.api

import com.jdc.restaurant.api.dto.Employee

object ClientContext {
    var loginUser: Employee? = null
    var token:String? = null
        set(value) {
            if(null != value) {
                field = "Bearer $value"
            }
        }
}