package com.jdc.restaurant.api.client

import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface CategoryClient {

    @GET("categories/types")
    fun findTypes():List<String>

    @GET("categories")
    fun findByType(@Query("type") type:String):List<Locale.Category>

}