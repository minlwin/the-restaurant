package com.jdc.restaurant.api.client

import com.jdc.restaurant.api.dto.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductClient {
    @GET("products/category/{id}")
    fun findByCategory(@Path("id") id:Int):List<Product>
}